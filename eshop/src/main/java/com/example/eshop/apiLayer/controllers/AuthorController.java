package com.example.eshop.apiLayer.controllers;

import com.example.eshop.businessLogicLayer.dtos.AuthorDTO;
import com.example.eshop.businessLogicLayer.dtos.BookDTO;
import com.example.eshop.businessLogicLayer.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public AuthorDTO create(@Valid @RequestBody AuthorDTO authorDTO) {
        return authorService.createAuthor(authorDTO);
    }

    @GetMapping("/{id}")
    public AuthorDTO get(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PutMapping("/{id}")
    public AuthorDTO update(@PathVariable Long id, @Valid @RequestBody AuthorDTO dto) {
        return authorService.updateAuthor(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }

    @GetMapping
    public List<AuthorDTO> list() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}/books")
    public Set<BookDTO> getBooksByAuthor(@PathVariable Long id) {
        return authorService.getBooksByAuthor(id);
    }
}