# Book & Author Management Web Application

## Overview
This project is a web application for managing books and authors. Users can add, edit, delete, and view books and authors. The application offers features for:

- **Book Management** (title, ISBN, category, year of publication, author(s), etc.)
- **Author Management** (name, list of books)
- **Assigning authors to books** and viewing books by author

---

## Implementation Details

### ✔️ Domain Layer (JPA, Hibernate, ManyToMany)
- The application uses JPA and Hibernate for database management.
- There are `Book` and `Author` entities annotated with `@Entity`.
- A `@ManyToMany` relationship between books and authors is implemented.
- Repository interfaces (`BookRepository`, `AuthorRepository`) use Spring Data JPA.

<div align="center">
<img src="https://raw.githubusercontent.com/GeorgiosVs/eshop-springboot/main/erd.png" alt="ER Diagram" width="600"/>
</div>

### ✔️ Business Logic Layer
- DTO classes (`BookDTO`, `AuthorDTO`) are used for communication between the client and server.
- Service interfaces (`BookService`, `AuthorService`) and their implementations (`BookServiceImpl`, `AuthorServiceImpl`) handle business logic.
- Services provide methods for adding, updating, deleting, and assigning authors to books.
- Input validation is implemented (e.g., `@NotBlank(message = "Title is required")` in `BookDTO`).

### 🛡️ Exception Handling
- Custom exception classes and global exception handlers have been added to improve error management.
- Proper HTTP status codes are returned for different error scenarios, such as not found, bad request, or internal server errors.
- Example: If a book or author is not found, the API responds with a clear message and proper status code (`404 Not Found`).

---

### ✔️ API Layer (REST API, Endpoints, Swagger)
- REST controllers (`BookController`, `AuthorController`) manage API endpoints:
  - `/books` — CRUD operations for books
  - `/books/{isbn}/authors` — assign authors to a book
  - `/authors` — CRUD operations for authors
  - `/authors/{id}/books` — list books for a given author
- Utilizes `@RestController`, `@RequestMapping`, and standard RESTful practices.
- API documentation is provided with Swagger (OpenAPI).

<div align="center">
<img src="https://raw.githubusercontent.com/GeorgiosVs/eshop-springboot/main/swaggerPhoto.png" alt="swaggerPhoto" width="600"/>
</div>

### 🚀 Swagger Documentation & Endpoints
The API is documented using Swagger, which provides an interactive UI to explore and test endpoints:

- **Getting Started**: Accessible via `/swagger-ui.html` (or the configured Swagger URL).
- **Available Endpoints** include:
  - `POST /books` — Create a new book
  - `GET /books/{isbn}` — Retrieve book details
  - `PUT /books/{isbn}` — Update book details
  - `DELETE /books/{isbn}` — Delete a book
  - `POST /books/{isbn}/authors` — Assign authors to a book
  - `GET /authors` — List all authors
  - `GET /authors/{id}/books` — List books by an author
  - `POST /authors` — Add a new author
  - `PUT /authors/{id}` — Update author info
  - `DELETE /authors/{id}` — Delete an author

Enjoy seamless interaction with the API through the Swagger UI, which also displays detailed request and response schemas.

---

### Prerequisites
- Java 24
- Maven

---
