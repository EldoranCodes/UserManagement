# 👤 User Management System

A Spring Boot REST API application for managing users, built as a learning project to explore Spring Boot, JPA/Hibernate, and RESTful API development.

## 📋 Table of Contents

- [About](#about)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Project Structure](#project-structure)
- [Architecture Diagram](#architecture-diagram)
- [Database Schema](#database-schema)
- [Configuration](#configuration)
- [Testing](#testing)
- [Learning Resources](#learning-resources)
- [Contributing](#contributing)
- [License](#license)

## 🎯 About

This is a learning project focused on building a RESTful User Management System using Spring Boot and JPA. The application demonstrates CRUD operations, database persistence, and REST API best practices.

**Learning Objectives:**
- Understanding Spring Boot fundamentals
- Working with Spring Data JPA and Hibernate
- Building RESTful APIs
- Database integration and management
- Exception handling and validation

## ✨ Features

- ✅ Create new users
- ✅ Retrieve user information (single and all users)
- ✅ Update existing user details
- ✅ Delete users
- ✅ Input validation
- ✅ Exception handling
- ✅ Database persistence with H2/MySQL/PostgreSQL

## 🛠 Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17+ | Programming Language |
| Spring Boot | 3.x | Application Framework |
| Spring Data JPA | 3.x | Data Access Layer |
| Hibernate | 6.x | ORM Framework |
| H2 Database | - | In-Memory Database (Dev) |
| MySQL/PostgreSQL | - | Production Database |
| Maven | 3.x | Build Tool |
| Lombok | - | Reduce Boilerplate Code |

## 📦 Prerequisites

Before running this application, make sure you have:

- **Java JDK 17 or higher** - [Download here](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.6+** - [Download here](https://maven.apache.org/download.cgi)
- **IDE** - IntelliJ IDEA, Eclipse, or VS Code
- **Postman** or **cURL** - For testing API endpoints
- **MySQL/PostgreSQL** (Optional) - For production database

To verify installations:
```bash
java -version
mvn -version
```

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/EldoranCodes/UserManagement.git
cd UserManagement
```

### 2. Configure Database

**Option A: Using H2 (In-Memory Database - Default)**

No additional configuration needed. The application will use H2 by default.

**Option B: Using MySQL**

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/usermanagement
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**Option C: Using PostgreSQL**

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/usermanagement
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Install Dependencies

```bash
mvn clean install
```

## 🏃 Running the Application

### Method 1: Using Maven

```bash
mvn spring-boot:run
```

### Method 2: Using Java JAR

```bash
mvn clean package
java -jar target/UserManagement-0.0.1-SNAPSHOT.jar
```

### Method 3: Using IDE

1. Import the project as a Maven project
2. Locate the main application class (usually `UserManagementApplication.java`)
3. Right-click and select "Run"

The application will start on `http://localhost:8080`

### Verify Application is Running

```bash
curl http://localhost:8080/actuator/health
```

## 📡 API Endpoints

### Base URL: `http://localhost:8080/api/v1`

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| GET | `/users` | Get all users | - |
| GET | `/users/{id}` | Get user by ID | - |
| POST | `/users` | Create new user | JSON (see below) |
| PUT | `/users/{id}` | Update user | JSON (see below) |
| DELETE | `/users/{id}` | Delete user | - |

### Request/Response Examples

#### Create User (POST `/users`)

**Request:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "+1234567890",
  "age": 30
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "+1234567890",
  "age": 30,
  "createdAt": "2026-01-14T10:30:00",
  "updatedAt": "2026-01-14T10:30:00"
}
```

#### Get All Users (GET `/users`)

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "+1234567890",
    "age": 30
  },
  {
    "id": 2,
    "firstName": "Jane",
    "lastName": "Smith",
    "email": "jane.smith@example.com",
    "phoneNumber": "+0987654321",
    "age": 28
  }
]
```

#### Update User (PUT `/users/{id}`)

**Request:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.updated@example.com",
  "phoneNumber": "+1234567890",
  "age": 31
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.updated@example.com",
  "phoneNumber": "+1234567890",
  "age": 31,
  "updatedAt": "2026-01-14T11:45:00"
}
```

#### Delete User (DELETE `/users/{id}`)

**Response (204 No Content)**

## 📁 Project Structure

```
UserManagement/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── eldorancodes/
│   │   │           └── usermanagement/
│   │   │               ├── UserManagementApplication.java
│   │   │               ├── controller/
│   │   │               │   └── UserController.java
│   │   │               ├── service/
│   │   │               │   ├── UserService.java
│   │   │               │   └── UserServiceImpl.java
│   │   │               ├── repository/
│   │   │               │   └── UserRepository.java
│   │   │               ├── model/
│   │   │               │   └── User.java
│   │   │               ├── dto/
│   │   │               │   ├── UserDTO.java
│   │   │               │   └── UserResponseDTO.java
│   │   │               └── exception/
│   │   │                   ├── GlobalExceptionHandler.java
│   │   │                   └── UserNotFoundException.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application-dev.properties
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── eldorancodes/
│                   └── usermanagement/
│                       ├── controller/
│                       ├── service/
│                       └── repository/
│
├── pom.xml
├── README.md
└── .gitignore
```

## 🏗 Architecture Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                        Client Layer                          │
│              (Postman, Browser, Mobile App)                  │
└────────────────────────┬────────────────────────────────────┘
                         │ HTTP Requests
                         ▼
┌─────────────────────────────────────────────────────────────┐
│                    Controller Layer                          │
│                   UserController.java                        │
│         • Handles HTTP requests                              │
│         • Validates input                                    │
│         • Returns HTTP responses                             │
└────────────────────────┬────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────┐
│                     Service Layer                            │
│                  UserServiceImpl.java                        │
│         • Business logic                                     │
│         • Transaction management                             │
│         • Data transformation                                │
└────────────────────────┬────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────┐
│                   Repository Layer                           │
│                  UserRepository.java                         │
│         • Data access operations                             │
│         • JPA queries                                        │
│         • Database interaction                               │
└────────────────────────┬────────────────────────────────────┘
                         │ JPA/Hibernate
                         ▼
┌─────────────────────────────────────────────────────────────┐
│                    Database Layer                            │
│                (H2 / MySQL / PostgreSQL)                     │
│         • Data persistence                                   │
│         • Data storage                                       │
└─────────────────────────────────────────────────────────────┘
```

## 🗄 Database Schema

### User Table

```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(20),
    age INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

### Entity-Relationship Diagram

```
┌─────────────────────────┐
│         User            │
├─────────────────────────┤
│ PK  id (Long)           │
│     firstName (String)  │
│     lastName (String)   │
│     email (String)      │
│     phoneNumber (String)│
│     age (Integer)       │
│     createdAt (DateTime)│
│     updatedAt (DateTime)│
└─────────────────────────┘
```

## ⚙️ Configuration

### Application Properties

Key configurations in `application.properties`:

```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# H2 Console (for development)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Logging
logging.level.org.springframework.web=INFO
logging.level.com.eldorancodes.usermanagement=DEBUG
```

### Environment Profiles

- **Development**: `application-dev.properties` - Uses H2 database
- **Production**: `application-prod.properties` - Uses MySQL/PostgreSQL

To run with a specific profile:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## 🧪 Testing

### Run All Tests

```bash
mvn test
```

### Run Specific Test Class

```bash
mvn test -Dtest=UserServiceTest
```

### Test Coverage

```bash
mvn clean test jacoco:report
```

View coverage report at: `target/site/jacoco/index.html`

## 📚 Learning Resources

### Spring Boot & JPA
- [Spring Boot Official Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Documentation](https://spring.io/projects/spring-data-jpa)
- [Baeldung Spring Tutorials](https://www.baeldung.com/spring-tutorial)

### Books
- "Spring in Action" by Craig Walls
- "Pro Spring Boot 2" by Felipe Gutierrez

### Videos
- Spring Boot Tutorial - Full Course (YouTube)
- JPA & Hibernate Tutorial (YouTube)

## 🤝 Contributing

This is a learning project, but contributions are welcome! If you'd like to contribute:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**EldoranCodes**
- GitHub: [@EldoranCodes](https://github.com/EldoranCodes)

## 🙏 Acknowledgments

- Spring Boot community for excellent documentation
- All the tutorials and resources that made learning Spring Boot enjoyable
- Open source community for inspiration

---

**Happy Coding! 🚀** If you found this project helpful, please give it a ⭐️

## 📞 Support

If you have questions or run into issues:
1. Check the [Issues](https://github.com/EldoranCodes/UserManagement/issues) page
2. Create a new issue with detailed information
3. Reach out via GitHub discussions

---

**Last Updated**: January 14 2026




