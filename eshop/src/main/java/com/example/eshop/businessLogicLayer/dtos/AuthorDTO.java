package com.example.eshop.businessLogicLayer.dtos;

import java.util.Set;

public class AuthorDTO {
    private Long id;
    private String name;
    private Set<String> bookIsbns;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<String> getBookIsbns() { return bookIsbns; }
    public void setBookIsbns(Set<String> bookIsbns) { this.bookIsbns = bookIsbns; }
}