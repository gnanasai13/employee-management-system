# Employee Management System

A server-rendered employee management application built with **Java, Spring Boot, Spring Data JPA, Thymeleaf, Maven, and MySQL**.

## Features

- View the employee list.
- Add and update employee records.
- View an individual employee's details.
- Delete employee records.

## Technology

| Area | Tools |
|---|---|
| Backend | Java, Spring Boot |
| Persistence | Spring Data JPA |
| UI | Thymeleaf |
| Database | MySQL |
| Build | Maven |

## Run locally

### Prerequisites

- JDK 8 or later (use the version supported by the project configuration)
- Maven
- MySQL

### Setup

1. Clone the repository and open the project directory.
2. Create a MySQL database for the application.
3. Update the datasource URL, username, and password in `src/main/resources/application.properties`.
4. Build and start the application:

```bash
mvn clean install
mvn spring-boot:run
```

5. Open [http://localhost:8080](http://localhost:8080).

> Keep database credentials outside source control. Do not commit personal credentials or production connection strings.

## Project notes

This is a learning project demonstrating a conventional Spring Boot application with server-rendered views and relational persistence.
