Task Manager – Spring Boot with JWT Authentication
Project Overview

This is a simple Task Manager web application built with Spring Boot with JWT-based authentication.

It allows users to register, login, and manage tasks (CRUD operations) securely using JWT tokens.

Tech Stack

Backend:
Java 17+
Spring Boot (Spring Data JPA, Spring Security, Spring Web)
JWT authentication (HS256)
MySQL
Maven


Endpoints

Authentication
| Method | Endpoint       | Description                            |
| ------ | -------------- | -------------------------------------- |
| POST   | /auth/register | Register a new user, returns JWT token |
| POST   | /auth/login    | Authenticate user, returns JWT token   |

Tasks (Protected, JWT required)
| Method | Endpoint    | Description                                                |
| ------ | ----------- | ---------------------------------------------------------- |
| POST   | /tasks      | Create a new task (Request from body (title, description)) |
| GET    | /tasks      | Get all tasks                                              |
| GET    | /tasks/{id} | Get a task by ID                                           |
| PUT    | /tasks/{id} | Update a task                                              |
| PUT    | /tasks/{id} | Update a status task (Request param completed true/false)  |
| DELETE | /tasks/{id} | Delete a task                                              |

Include JWT token in header:
Authorization: Bearer <your_token_here>


Example Requests (Postman)

Login:

POST /api/auth/login
Content-Type: application/json

{
  "username": "mariana",
  "password": "mypassword"
}


Create Task:

POST /api/tasks
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json

{
  "title": "Finish project",
  "description": "Complete Spring Boot Task Manager",
}

Setup Instructions

1. Clone the repository:

git clone https://github.com/yourusername/task-manager-spring-jwt.git
cd task-manager-spring-jwt

2. Setup MySQL Database

Create the database manually in MySQL:

CREATE DATABASE taskdb;

Spring Boot will handle the tables automatically. No need to create tables manually.

Configure MySQL database access in application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/taskdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Notes:
spring.jpa.hibernate.ddl-auto=update → Spring will automatically create or update tables based on your entities.

spring.jpa.show-sql=true → Prints SQL queries in the console for debugging.

Only the database itself (taskdb) needs to exist. All tables are created at runtime if they don’t exist.

3. Build and run:

mvn clean install
mvn spring-boot:run


4. Test endpoints with Postman using JWT tokens.
