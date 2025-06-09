package com.example.eshop.domainLayer.repositories;

import com.example.eshop.domainLayer.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long>{ }