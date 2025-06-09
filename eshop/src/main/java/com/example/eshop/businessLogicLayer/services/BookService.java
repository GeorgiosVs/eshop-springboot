package com.example.eshop.businessLogicLayer.services;

import com.example.eshop.businessLogicLayer.dtos.BookDTO;
import java.util.List;
import java.util.Set;

public interface BookService {
    BookDTO createBook(BookDTO bookDTO);
    BookDTO updateBook(String isbn, BookDTO bookDTO);
    void deleteBook(String isbn);
    BookDTO getBookByIsbn(String isbn);
    List<BookDTO> getAllBooks();
    BookDTO assignAuthors(String isbn, Set<Long> authorIds);
}