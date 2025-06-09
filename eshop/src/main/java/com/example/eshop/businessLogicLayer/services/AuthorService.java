package com.example.eshop.businessLogicLayer.services;

import com.example.eshop.businessLogicLayer.dtos.AuthorDTO;
import com.example.eshop.businessLogicLayer.dtos.BookDTO;
import java.util.List;
import java.util.Set;

public interface AuthorService {
    AuthorDTO createAuthor(AuthorDTO authorDTO);
    AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO);
    void deleteAuthor(Long id);
    AuthorDTO getAuthorById(Long id);
    List<AuthorDTO> getAllAuthors();
    Set<BookDTO> getBooksByAuthor(Long authorId);
}