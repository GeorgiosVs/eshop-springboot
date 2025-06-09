package com.example.eshop.businessLogicLayer.services;

import com.example.eshop.businessLogicLayer.dtos.AuthorDTO;
import com.example.eshop.businessLogicLayer.dtos.BookDTO;
import com.example.eshop.domainLayer.entities.Author;
import com.example.eshop.domainLayer.entities.Book;
import com.example.eshop.domainLayer.repositories.AuthorRepository;
import com.example.eshop.domainLayer.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        authorRepository.save(author);
        return convertToDTO(author);
    }

    @Override
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        Optional<Author> optAuthor = authorRepository.findById(id);
        if (!optAuthor.isPresent()) {
            throw new RuntimeException("Author not found");
        }
        Author author = optAuthor.get();
        author.setName(authorDTO.getName());
        authorRepository.save(author);
        return convertToDTO(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Author not found");
        }
        authorRepository.deleteById(id);
    }

    @Override
    public AuthorDTO getAuthorById(Long id) {
        Optional<Author> authorOpt = authorRepository.findById(id);
        if (!authorOpt.isPresent()) {
            throw new RuntimeException("Author not found");
        }
        return convertToDTO(authorOpt.get());
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                      .map(this::convertToDTO)
                      .collect(Collectors.toList());
    }

    @Override
    public Set<BookDTO> getBooksByAuthor(Long authorId) {
        Optional<Author> authorOpt = authorRepository.findById(authorId);
        if (!authorOpt.isPresent()) {
            throw new RuntimeException("Author not found");
        }
        Set<Book> books = authorOpt.get().getBooks();
        // Χρησιμοποιούμε τη δική μας μέθοδο μετατροπής Book -> BookDTO
        return books.stream()
                    .map(this::convertBookToDTO)
                    .collect(Collectors.toSet());
    }

    // Μετατροπή Author σε AuthorDTO
    private AuthorDTO convertToDTO(Author author) {
        if (author == null) return null;
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        if (author.getBooks() != null) {
            Set<String> bookIsbns = author.getBooks().stream()
                                            .map(Book::getIsbn)
                                            .collect(Collectors.toSet());
            dto.setBookIsbns(bookIsbns);
        }
        return dto;
    }

    // Νέα μέθοδος για μετατροπή Book σε BookDTO
    private BookDTO convertBookToDTO(Book book) {
        if (book == null) return null;
        BookDTO dto = new BookDTO();
        dto.setIsbn(book.getIsbn());
        dto.setTitle(book.getTitle());
        dto.setPublisher(book.getPublisher());
        dto.setPublishedYear(book.getPublishedYear());
        return dto;
    }
}