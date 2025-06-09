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
<img src="https://raw.githubusercontent.com/GeorgiosVs/eshop-springboot/main/erd.png" alt="ER Diagram" width="400"/>
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
<img src="https://raw.githubusercontent.com/GeorgiosVs/eshop-springboot/main/swagger1.png" alt="swaggerPhoto" width="600"/>
</div>

### 🚀 Swagger Documentation & Endpoints
The API is documented using Swagger, which provides an interactive UI to explore and test endpoints.

### Getting Started:
Accessible via `/swagger-ui.html` (or the configured Swagger URL).

### Available Endpoints:
- `POST /books` — Create a new book
- `GET /books/{isbn}` — Retrieve details of a book by ISBN
- `PUT /books/{isbn}` — Update details of a book by ISBN
- `DELETE /books/{isbn}` — Delete a book by ISBN
- `PUT /books/{isbn}/authors` — Assign authors to a book
- `GET /books` — List all books
- `POST /authors` — Add a new author
- `GET /authors` — List all authors
- `GET /authors/{id}` — Get details of an author
- `PUT /authors/{id}` — Update author information
- `DELETE /authors/{id}` — Delete an author
- `GET /authors/{id}/books` — List all books by an author

---

<div align="center">
<img src="https://raw.githubusercontent.com/GeorgiosVs/eshop-springboot/main/erd2.svg" alt="ER Diagram 2" width="600"/>
</div>

# `eshop_spring` Database
This repository describes the structure of the `eshop_spring` database used for managing books and authors in an online shop environment.
## Database Overview
The database contains three main tables:
- **`authors`**: Stores information about authors.
- **`books`**: Stores details about books.
- **`books_authors`**: Manages the many-to-many relationship between books and authors.

## Authors Table

| Column | Data Type | Description | Notes |
| --- | --- | --- | --- |
| `id` | `bigint` | Author's unique ID (Primary Key) | Auto-incremented |
| `name` | `varchar(100)` | Name of the author |  |
| `nationality` | `varchar(100)` | Nationality of the author | NULL allowed |
| `birth_date` | `date` | Date of birth | NULL allowed |
## Books Table

| Column | Data Type | Description | Notes |
| --- | --- | --- | --- |
| `isbn` | `varchar(20)` | Book's ISBN code (Primary Key) |  |
| `title` | `varchar(255)` | Title of the book |  |
| `publisher` | `varchar(100)` | Publisher name | NULL allowed |
| `published_year` | `int` | Year of publication | NULL allowed |
## Books_Authors Table

| Column | Data Type | Description | Notes |
| --- | --- | --- | --- |
| `book_isbn` | `varchar` | ISBN of the book | Foreign key to `books.isbn` |
| `author_id` | `bigint` | ID of the author | Foreign key to `authors.id` |
| **Primary Key** | Composite of `book_isbn` and `author_id` | Ensures unique book-author pairs |  |
## Diagram Overview


---
<div align="center">
<img src="https://raw.githubusercontent.com/GeorgiosVs/eshop-springboot/main/database.svg" alt="db Diagram" width="600"/>
</div>

### Prerequisites
- Java 24
- Maven

---
