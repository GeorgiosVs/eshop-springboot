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

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    // Constructor Injection
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setPublisher(bookDTO.getPublisher());
        book.setPublishedYear(bookDTO.getPublishedYear());
        if (bookDTO.getAuthorIds() != null && !bookDTO.getAuthorIds().isEmpty()) {
            Set<Author> authors = new HashSet<>(authorRepository.findAllById(bookDTO.getAuthorIds()));
            book.setAuthors(authors);
        }
        bookRepository.save(book);
        return toDTO(book);
    }

    @Override
    public BookDTO updateBook(String isbn, BookDTO bookDTO) {
        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new NoSuchElementException("Book not found with isbn: " + isbn));
        book.setTitle(bookDTO.getTitle());
        book.setPublisher(bookDTO.getPublisher());
        book.setPublishedYear(bookDTO.getPublishedYear());
        if (bookDTO.getAuthorIds() != null) {
            Set<Author> authors = new HashSet<>(authorRepository.findAllById(bookDTO.getAuthorIds()));
            book.setAuthors(authors);
        }
        bookRepository.save(book);
        return toDTO(book);
    }

    @Override
    public void deleteBook(String isbn) {
        bookRepository.deleteById(isbn);
    }

    @Override
    public BookDTO getBookByIsbn(String isbn) {
        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new NoSuchElementException("Book not found with isbn: " + isbn));
        return toDTO(book);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO assignAuthors(String isbn, Set<Long> authorIds) {
        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new NoSuchElementException("Book not found with isbn: " + isbn));
        Set<Author> authors = new HashSet<>(authorRepository.findAllById(authorIds));
        book.setAuthors(authors);
        bookRepository.save(book);
        return toDTO(book);
    }

    private BookDTO toDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setIsbn(book.getIsbn());
        dto.setTitle(book.getTitle());
        dto.setPublisher(book.getPublisher());
        dto.setPublishedYear(book.getPublishedYear());
        if (book.getAuthors() != null) {
            dto.setAuthorIds(
                book.getAuthors().stream()
                        .map(Author::getId)
                        .collect(Collectors.toSet())
            );
        }
        return dto;
    }
}