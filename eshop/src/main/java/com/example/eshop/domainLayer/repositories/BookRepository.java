package com.example.eshop.domainLayer.repositories;

import com.example.eshop.domainLayer.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String>{ }