# CineHub REST API

## Project Overview
CineHub is a RESTful API designed to manage a database of movies, directors, and categories.  
It provides complete CRUD (Create, Read, Update, Delete) functionality for all three entities, with advanced filtering and search capabilities.

---

## Technology Stack

- **Language:** Java 17+
- **Framework:** Spring Boot 3+
- **Web:** Spring Web MVC
- **Persistence:** Spring Data JPA / Hibernate
- **Database:** MySQL
- **Server:** Apache Tomcat

---

## Setup and Running the Project

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- Apache Maven
- A running MySQL instance

---

### 1. Database Configuration
Update your database connection settings in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cinehub_db?serverTimezone=UTC
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
2. Build and Deploy
Open your terminal in the project root directory and build the project using Maven:

bash
Copier le code
mvn clean package
Deploy the resulting WAR file (e.g., cinehub.war) to your Tomcat server.

API Base URL:

bash
Copier le code
http://localhost:8080/cinehub
API Initialization and Reference
Since data initialization was removed, you must use the following POST requests to create the necessary Category and Director records before attempting to create a Movie.

Step 1: Initialize Data (POST Requests)
Entity	Method	Endpoint	Example JSON Body (Use in Postman)
Category	POST	/categories	```json
{ "name": "Science Fiction" }			

|
Copier le code
| Director  | POST    | `/directors`  | ```json
{ 
  "firstName": "Christopher", 
  "lastName": "Nolan", 
  "birthDate": "1970-07-30", 
  "nationality": "British-American", 
  "biography": "Acclaimed filmmaker."
}
``` |

> **Note:** The IDs returned for the Category and Director are required for the Movie creation.

---

### Step 2: Create a Movie (POST Request)

| Entity | Method | Endpoint  | Example JSON Body (Use in Postman) |
|---------|---------|------------|------------------------------------|
| Movie   | POST    | `/movies`  | ```json
{
  "title": "Inception",
  "description": "Mind-bending heist.",
  "synopsis": "Dreams within dreams.",
  "rating": 9.2,
  "duration": 148,
  "releaseDate": "2010-07-16",
  "directorId": 1,
  "categoryId": 1
}
``` |

---

## API Reference: Endpoints for Testing

### 1. Category Endpoints (`/categories`)

| Method | Endpoint | Description |
|---------|-----------|-------------|
| GET | `/categories` | Retrieve all categories. |
| GET | `/categories/{id}` | Retrieve a category by ID. |
| PUT | `/categories/{id}` | Update an existing category. |
| DELETE | `/categories/{id}` | Delete a category. |

---

### 2. Director Endpoints (`/directors`)

| Method | Endpoint | Description |
|---------|-----------|-------------|
| GET | `/directors` | Retrieve all directors. |
| GET | `/directors/{id}` | Retrieve a director by ID. |
| GET | `/directors/search?firstName=...&lastName=...` | Search by exact First Name AND Last Name. |
| PUT | `/directors/{id}` | Update an existing director. |
| DELETE | `/directors/{id}` | Delete a director. |

---

### 3. Movie Endpoints (`/movies`)

| Method | Endpoint | Description |
|---------|-----------|-------------|
| GET | `/movies` | Retrieve all movies. |
| GET | `/movies/{id}` | Retrieve a movie by ID. |
| PUT | `/movies/{id}` | Update an existing movie. |
| DELETE | `/movies/{id}` | Delete a movie. |

---

### 4. Movie Filtering & Search

| Method | Endpoint | Description |
|---------|-----------|-------------|
| GET | `/movies/title/{title}` | US5: Search by exact movie title. |
| GET | `/movies/filter/year/{year}` | US7: Filter by release year. |
| GET | `/movies/filter/min-rating/{minRating}` | US8: Filter by minimum rating. |
| GET | `/movies/category/{categoryId}` | US19: Filter by Category ID. |

---