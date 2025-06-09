![ER Diagram](https://raw.githubusercontent.com/GeorgiosVs/eshop-springboot/main/erDiagram.svg)
# Book & Author Management Web Application

## Overview
This project is a web application for managing books and authors. Users can add, edit, and delete books and authors. The application offers features for:

- **Book Management** (title, ISBN, category, year of publication, author(s), etc.)
- **Author Management** (name, list of books)

---

## Implementation Details

### ✔️ Domain Layer (JPA, Hibernate, ManyToMany)
- The application uses JPA and Hibernate for database management.
- There are `Book` and `Author` entities annotated with `@Entity`.
- A `@ManyToMany` relationship between books and authors is implemented.
- Repository interfaces (`BookRepository`, `AuthorRepository`) use Spring Data JPA.

---

### ✔️ Business Logic Layer
- DTO classes (`BookDTO`, `AuthorDTO`) are used for communication between the client and server.
- Service interfaces (`BookService`, `AuthorService`) and implementations (`BookServiceImpl`, `AuthorServiceImpl`) handle business logic.
- Services provide methods for adding, updating, deleting, and assigning authors to books.
- Input validation is implemented (e.g., `@NotBlank(message = "Title is required")` in `BookDTO`).

---

### ✔️ API Layer (REST API, Endpoints, Swagger)
- REST controllers (`BookController`, `AuthorController`) manage API endpoints:
    - `/books` — CRUD operations for books
    - `/books/{id}/authors` — assign authors to a book
    - `/authors` — CRUD operations for authors
    - `/authors/{id}/books` — list books for a given author
- Utilizes `@RestController`, `@RequestMapping`, and standard RESTful practices.
- API documentation is provided with Swagger (OpenAPI).

---

## Getting Started

### Prerequisites
- Java 24
- Maven
