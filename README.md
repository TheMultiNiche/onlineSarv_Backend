# Spring Boot Authentication Application

A complete Spring Boot application with JWT authentication, user registration, login, and password reset functionality.

## Features

- User registration with email and encrypted password
- User login with JWT token generation
- Password encryption using BCrypt
- Forgot password functionality
- Reset password with token validation
- JWT-based security
- H2 in-memory database for development

## API Endpoints

### Authentication Endpoints

1. **POST /api/auth/signup** - User Registration
   ```json
   {
     "email": "user@example.com",
     "password": "password123"
   }
   ```

2. **POST /api/auth/login** - User Login
   ```json
   {
     "email": "user@example.com",
     "password": "password123"
   }
   ```

3. **POST /api/auth/forgot-password** - Forgot Password
   ```json
   {
     "email": "user@example.com"
   }
   ```

4. **POST /api/auth/reset-password** - Reset Password
   ```json
   {
     "resetToken": "reset-token-here",
     "newPassword": "newpassword123"
   }
   ```

5. **GET /api/auth/profile** - Get User Profile (requires JWT token)
   - Header: `Authorization: Bearer <jwt-token>`

## How to Run

1. Make sure you have Java 17+ installed
2. Run the application:
   ```bash
   mvn spring-boot:run
   ```
3. The application will start on `http://localhost:8080`

## Database

- Uses H2 in-memory database
- H2 Console available at: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

## Security

- Passwords are encrypted using BCrypt
- JWT tokens are used for authentication
- JWT secret and expiration are configurable in `application.properties`
- All authentication endpoints are public
- Profile endpoint requires valid JWT token

## Testing the API

You can test the API using tools like Postman or curl:

### Register a new user:
```bash
curl -X POST http://localhost:8080/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{"email": "test@example.com", "password": "password123"}'
```

### Login:
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email": "test@example.com", "password": "password123"}'
```

### Access protected endpoint:
```bash
curl -X GET http://localhost:8080/api/auth/profile \
  -H "Authorization: Bearer <your-jwt-token>"
```
