# Login Signup API

A simple Spring Boot REST API for user registration, login, and fetching all stored users.

## Project Overview

This project provides:

- User registration with password hashing
- User login by email and password
- Retrieval of all registered users
- PostgreSQL persistence using Spring Data JPA
- Basic request validation using Jakarta Validation

## Tech Stack

- Java 21
- Spring Boot 4.1.0
- Spring Web MVC
- Spring Data JPA
- Spring Security
- Validation
- PostgreSQL
- Lombok

## Project Structure

```text
src/main/java/com/example/login_signup
├── LoginSignupApplication.java
├── config
│   ├── PasswordConfig.java
│   └── SecurityConfig.java
├── controller
│   └── AuthController.java
├── dto
│   ├── UserDetailsDto.java
│   ├── UserLoginDto.java
│   └── UserRegistrationDto.java
├── entity
│   └── User.java
├── exception
│   └── GlobalException.java
├── repository
│   └── UserRepository.java
└── service
    └── UserService.java

src/main/resources
└── application.properties
```

## Configuration

The application uses PostgreSQL. Update `src/main/resources/application.properties` as needed:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/Users
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

## Running the Application

1. Make sure PostgreSQL is running.
2. Create a database named `Users` or update the datasource URL.
3. Run the app from your IDE or with Maven.

```bash
./mvnw spring-boot:run
```

The application starts on the default Spring Boot port:

```text
http://localhost:8080
```

## API Endpoints

Base path: `/user`

### 1. Register User

- Method: `POST`
- Endpoint: `/user/register`
- Request body: `UserRegistrationDto`

#### Sample Request

```json
{
  "userName": "john_doe",
  "email": "john@example.com",
  "password": "password123"
}
```

#### Success Response

```text
User Created Successfully
```

#### Possible Error Response

```text
Email already exists
```

### 2. Login User

- Method: `POST`
- Endpoint: `/user/login`
- Request body: `UserLoginDto`

#### Sample Request

```json
{
  "email": "john@example.com",
  "password": "password123"
}
```

#### Success Response

```text
Login Successful!
```

#### Possible Error Responses

```text
No user found
```

```text
Login Unsuccessfull!
```

### 3. Get All Users

- Method: `GET`
- Endpoint: `/user`

#### Success Response

```json
[
  {
    "userName": "john_doe",
    "email": "john@example.com"
  },
  {
    "userName": "jane_doe",
    "email": "jane@example.com"
  }
]
```

## Request DTOs

### UserRegistrationDto

```json
{
  "userName": "john_doe",
  "email": "john@example.com",
  "password": "password123"
}
```

Validation rules:

- `userName` must not be blank
- `email` must not be blank and must be a valid email address
- `password` must not be blank and must contain at least 8 characters

### UserLoginDto

```json
{
  "email": "john@example.com",
  "password": "password123"
}
```

Validation rules:

- `email` must not be blank and must be a valid email address
- `password` must not be blank and must contain at least 8 characters

## Response DTO

### UserDetailsDto

Returned by `GET /user`:

```json
{
  "userName": "john_doe",
  "email": "john@example.com"
}
```

## Database Model

The `User` entity is stored in the `users` table.

Fields:

- `id` - auto-generated primary key
- `userName`
- `email`
- `password` - stored as a BCrypt hash

## Security Notes

- CSRF is disabled
- All endpoints are currently open to everyone (`permitAll`)
- HTTP Basic is enabled, but the API does not require authentication for the current endpoints

## Important Notes

- Passwords are never stored in plain text
- Duplicate emails are rejected during registration
- The project currently returns plain text messages for register/login actions

## Example cURL Requests

### Register

```bash
curl -X POST http://localhost:8080/user/register \
  -H "Content-Type: application/json" \
  -d '{"userName":"john_doe","email":"john@example.com","password":"password123"}'
```

### Login

```bash
curl -X POST http://localhost:8080/user/login \
  -H "Content-Type: application/json" \
  -d '{"email":"john@example.com","password":"password123"}'
```

### Get All Users

```bash
curl http://localhost:8080/user
```

