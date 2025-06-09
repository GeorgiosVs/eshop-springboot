package com.example.eshop.apiLayer.controllers;

import com.example.eshop.businessLogicLayer.dtos.BookDTO;
import com.example.eshop.businessLogicLayer.services.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookDTO create(@Valid @RequestBody BookDTO bookDTO) {
        return bookService.createBook(bookDTO);
    }

    @GetMapping("/{isbn}")
    public BookDTO get(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @PutMapping("/{isbn}")
    public BookDTO update(@PathVariable String isbn, @Valid @RequestBody BookDTO dto) {
        return bookService.updateBook(isbn, dto);
    }

    @DeleteMapping("/{isbn}")
    public void delete(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
    }

    @GetMapping
    public List<BookDTO> list() {
        return bookService.getAllBooks();
    }

    @PutMapping("/{isbn}/authors")
    public BookDTO assignAuthors(@PathVariable String isbn, @RequestBody Set<Long> authorIds) {
        return bookService.assignAuthors(isbn, authorIds);
    }
}