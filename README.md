# Employee Management System

A production-grade RESTful API built with Java 17 and Spring Boot 3.x for managing employee data with secure JWT-based authentication.

## Tech Stack

- **Java 17**
- **Spring Boot 3.x**
- **Spring Security + JWT**
- **Spring Data JPA + Hibernate**
- **MySQL**
- **Lombok**
- **Maven**

## Features

- Employee CRUD operations
- DTO pattern with request/response separation
- Input validation with meaningful error messages
- Global exception handling
- JWT authentication and authorization (in progress)
- Clean REST API design with proper HTTP status codes

## API Endpoints

### Employee APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/employee | Create new employee |
| GET | /api/employee | Get all employees |
| GET | /api/employee/{id} | Get employee by ID |
| PUT | /api/employee/{id} | Update employee |
| DELETE | /api/employee/{id} | Delete employee |

## Getting Started

### Prerequisites

- Java 17
- MySQL 8.x
- Maven

### Setup

1. Clone the repository
```bash
   git clone https://github.com/iMohammedSufiyan/employee-management.git
```

2. Create MySQL database
```sql
   CREATE DATABASE employee_management;
```

3. Configure `application.properties`
```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/employee_management
   spring.datasource.username=your_username
   spring.datasource.password=your_password
```

4. Run the application
```bash
   mvn spring-boot:run
```

## Project Structure

src/main/java/com/sufiyan/employee_management/
├── controller/
├── service/
│   └── impl/
├── repository/
├── entity/
├── dto/
└── exception/

## Author

**Mohammed Sufiyan**  
Java Backend Developer  
