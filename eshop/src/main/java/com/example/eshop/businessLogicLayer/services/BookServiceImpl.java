package com.example.eshop.businessLogicLayer.services;

import com.example.eshop.businessLogicLayer.dtos.BookDTO;
import com.example.eshop.domainLayer.entities.Book;
import com.example.eshop.domainLayer.entities.Author;
import com.example.eshop.domainLayer.repositories.BookRepository;
import com.example.eshop.domainLayer.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        if (bookRepository.existsById(bookDTO.getIsbn())) {
            throw new RuntimeException("Book with this ISBN already exists");
        }
        Book newBook = new Book();
        newBook.setIsbn(bookDTO.getIsbn());
        newBook.setTitle(bookDTO.getTitle());
        newBook.setPublisher(bookDTO.getPublisher());
        newBook.setPublishedYear(bookDTO.getPublishedYear());

        // Save the book first
        bookRepository.save(newBook);
        return convertToDTO(newBook);
    }

    @Override
    public BookDTO updateBook(String isbn, BookDTO bookDTO) {
        Optional<Book> optionalBook = bookRepository.findById(isbn);
        if (!optionalBook.isPresent()) {
            throw new RuntimeException("Book not found");
        }
        Book book = optionalBook.get();
        book.setTitle(bookDTO.getTitle());
        book.setPublisher(bookDTO.getPublisher());
        book.setPublishedYear(bookDTO.getPublishedYear());

        bookRepository.save(book);
        return convertToDTO(book);
    }

    @Override
    public void deleteBook(String isbn) {
        if (!bookRepository.existsById(isbn)) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(isbn);
    }

    @Override
    public BookDTO getBookByIsbn(String isbn) {
        Optional<Book> bookOpt = bookRepository.findById(isbn);
        if (!bookOpt.isPresent()) {
            throw new RuntimeException("Book not found");
        }
        return convertToDTO(bookOpt.get());
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public BookDTO assignAuthors(String isbn, Set<Long> authorIds) {
        Optional<Book> bookOpt = bookRepository.findById(isbn);
        if (!bookOpt.isPresent()) {
            throw new RuntimeException("Book not found");
        }
        Book book = bookOpt.get();
        Set<Author> authors = new HashSet<>();
        for (Long authorId : authorIds) {
            Optional<Author> authorOpt = authorRepository.findById(authorId);
            if (authorOpt.isPresent()) {
                authors.add(authorOpt.get());
            } else {
                throw new RuntimeException("Author with ID " + authorId + " not found");
            }
        }
        book.setAuthors(authors);
        bookRepository.save(book);
        return convertToDTO(book);
    }


    private BookDTO convertToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setIsbn(book.getIsbn());
        dto.setTitle(book.getTitle());
        dto.setPublisher(book.getPublisher());
        dto.setPublishedYear(book.getPublishedYear());
        if (book.getAuthors() != null) {
            Set<Long> authorIds = book.getAuthors().stream().map(Author::getId).collect(Collectors.toSet());
            dto.setAuthorIds(authorIds);
        }
        return dto;
    }
}
