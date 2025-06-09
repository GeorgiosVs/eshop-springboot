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

# EShop Spring Database

This repository contains the database schema for `eshop_spring`, an e-commerce application.

---

## Database Description

The `eshop_spring` database includes the following tables:

### `authors` table
This table records authors of books.

| Column          | Data Type             | Description                               | Notes                        |
|-----------------|-----------------------|-------------------------------------------|------------------------------|
| `id`            | `bigint(20)`          | Author's ID (Primary Key, Auto Increment) |                              |
| `name`          | `varchar(100)`        | Author's name                            |                              |
| `nationality`   | `varchar(100)`        | Nationality                              | NULL allowed                 |
| `birth_date`    | `date`                | Date of birth                            | NULL allowed                 |

### `books` table
This table records book details.

| Column            | Data Type             | Description                                | Notes                       |
|-------------------|-----------------------|--------------------------------------------|------------------------------|
| `isbn`            | `varchar(20)`         | Book's ISBN code (Primary Key)             |                              |
| `title`           | `varchar(255)`        | Book title                                |                              |
| `publisher`       | `varchar(100)`        | Publisher                                  | NULL allowed                 |
| `published_year`  | `int(11)`             | Year of publication                         | NULL allowed                 |
| `category`        | `varchar`             | Book category or genre                     | NULL allowed                 |
| `created_at`      | `timestamp`           | Record creation timestamp                  |                              |
| `updated_at`      | `timestamp`           | Record last update timestamp               |                              |

### `book_author` table
This table manages the many-to-many relationship between books and authors.

| Column          | Data Type         | Description                              | Notes                        |
|-----------------|-------------------|------------------------------------------|------------------------------|
| `book_isbn`     | `varchar`         | ISBN of the book                         | Foreign Key to `Book.isbn`  |
| `author_id`     | `bigint`          | ID of the author                         | Foreign Key to `Author.id`  |
| **Primary Key** | Composite of `book_isbn` and `author_id` | Ensures unique book-author pairs |

---

## Additional Information
- The database supports multiple authors per book through the `book_author` join table.
- The schema allows for flexible many-to-many relationships between books and authors.
- The `created_at` and `updated_at` fields facilitate tracking record history.

---
<div align="center">
<img src="https://raw.githubusercontent.com/GeorgiosVs/eshop-springboot/main/dbERD.svg" alt="dbERD" width="600"/>
</div>


### Prerequisites
- Java 24
- Maven

---
