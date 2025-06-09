package com.example.eshop.businessLogicLayer.services;

import com.example.eshop.businessLogicLayer.dtos.AuthorDTO;
import com.example.eshop.businessLogicLayer.dtos.BookDTO;
import com.example.eshop.domainLayer.entities.Author;
import com.example.eshop.domainLayer.entities.Book;
import com.example.eshop.domainLayer.repositories.AuthorRepository;
import com.example.eshop.domainLayer.repositories.BookRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        if (authorDTO.getBookIsbns() != null && !authorDTO.getBookIsbns().isEmpty()) {
            Set<Book> books = new HashSet<>(bookRepository.findAllById(authorDTO.getBookIsbns()));
            author.setBooks(books);
        }
        authorRepository.save(author);
        return toDTO(author);
    }

    @Override
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Author not found with id: " + id));
        author.setName(authorDTO.getName());
        if (authorDTO.getBookIsbns() != null) {
            Set<Book> books = new HashSet<>(bookRepository.findAllById(authorDTO.getBookIsbns()));
            author.setBooks(books);
        }
        authorRepository.save(author);
        return toDTO(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Author not found with id: " + id));
        return toDTO(author);
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Set<BookDTO> getBooksByAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new NoSuchElementException("Author not found with id: " + authorId));
        return author.getBooks().stream()
                .map(this::toBookDTO)
                .collect(Collectors.toSet());
    }

    private AuthorDTO toDTO(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        if (author.getBooks() != null) {
            dto.setBookIsbns(
                author.getBooks().stream()
                        .map(Book::getIsbn)
                        .collect(Collectors.toSet())
            );
        }
        return dto;
    }

    private BookDTO toBookDTO(Book book) {
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