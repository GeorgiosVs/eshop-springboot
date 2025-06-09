package com.example.eshop.businessLogicLayer.dtos;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;

public class BookDTO {
    private String isbn;

    @NotBlank(message = "Title is required")
    private String title;

    private String publisher;
    private Integer publishedYear;
    private Set<Long> authorIds;

    // Getters & Setters
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public Integer getPublishedYear() { return publishedYear; }
    public void setPublishedYear(Integer publishedYear) { this.publishedYear = publishedYear; }

    public Set<Long> getAuthorIds() { return authorIds; }
    public void setAuthorIds(Set<Long> authorIds) { this.authorIds = authorIds; }
}