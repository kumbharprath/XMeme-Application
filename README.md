# 😂 XMeme – Backend Web Application

A **personal backend project** built using **Spring Boot** that powers a simple meme-sharing platform. This application exposes RESTful APIs to post memes, fetch the latest memes, and retrieve memes by ID, following clean architecture and best backend practices.

---

## 🚀 Project Overview

**XMeme** is a backend-only web application developed from scratch to strengthen hands-on experience with:
- Spring Boot REST API development
- MVC layered architecture
- MongoDB integration
- API documentation and testing
- Containerization using Docker

The project focuses on **clarity, correctness, and best practices**, making it ideal as a learning and showcase project.

---

## 🏗️ Architecture

The application follows a **layered MVC architecture**:

- **Controller Layer** – Handles HTTP requests and responses
- **Service Layer** – Contains business logic and validations
- **Repository Layer** – Manages database interactions using Spring Data MongoDB
- **Model Layer** – Represents MongoDB documents

This structure improves maintainability and separation of concerns.

---

## 🛠️ Tech Stack

- **Language:** Java
- **Framework:** Spring Boot
- **Database:** MongoDB
- **Caching:** Redis
- **API Style:** RESTful APIs
- **Documentation:** Swagger (OpenAPI)
- **Containerization:** Docker
- **Testing:** Mockito
- **Serialization:** Jackson

- **Language:** Java
- **Framework:** Spring Boot
- **Database:** MongoDB
- **API Style:** RESTful APIs
- **Documentation:** Swagger (OpenAPI)
- **Containerization:** Docker
- **Testing:** Mockito
- **Serialization:** Jackson

---

## 📦 Features

### 🚀 Caching Optimization (Redis)

- Integrated **Redis caching** at the **service layer** to optimize read performance
- Applied caching specifically on the **`getMemeById`** service method
- Reduced repeated database hits for frequently accessed memes
- Improved response time and overall API efficiency


### 📝 Meme Management APIs

- **POST /memes**  
  Add a new meme with name, caption, and image URL

- **GET /memes**  
  Fetch the latest **100 memes**, sorted by most recent

- **GET /memes/{id}**  
  Retrieve a specific meme using its unique ID

---

### 📝 Meme Management APIs

- **POST /memes**  
  Add a new meme with name, caption, and image URL

- **GET /memes**  
  Fetch the latest **100 memes**, sorted by most recent

- **GET /memes/{id}**  
  Retrieve a specific meme using its unique ID  
  *(Cached using Redis at the service layer)*

---

## 🧪 Validation & Error Handling

- Request payload validation for required fields
- Proper HTTP status codes (`200`, `201`, `400`, `404`)
- Graceful handling of invalid requests and missing resources

---

## 📡 API Documentation

- Integrated **Swagger UI** for interactive API documentation
- Allows easy testing and visualization of endpoints

Once the application is running, Swagger can be accessed at:
```
http://localhost:8080/swagger-ui.html
```

---

## 🧪 Testing

- Unit testing of service and controller layers using **Mockito**
- Focused on validating business logic and API behavior

---

## 🐳 Docker Support

- Application containerized using **Docker**
- Enables consistent execution across different environments

### Build Docker Image

```bash
docker build -t xmeme-backend .
```

### Run Container

```bash
docker run -p 8080:8080 xmeme-backend
```

---

## ⚙️ Configuration

- MongoDB connection configured via `application.properties`
- Uses Spring Data MongoDB repositories for persistence

---

## 📁 Project Structure

```
src/main/java
├── config            # Redis Configuration
├── controller        # REST controllers
├── data              # Entity
├── exceptionhandler  # global exeption handlingBusiness logic
├── exchange          # Request and Response DTOs
├── mapper            # Object Mapper classes
├── repository        # MongoDB repositories
└── service           # Service Layer 
```

---

## 🎯 Learning Outcomes

- Hands-on experience with Spring Boot REST APIs
- MongoDB integration using Spring Data
- Implementing **Redis caching at the service layer**
- Cache optimization for read-heavy endpoints
- API documentation using Swagger
- Unit testing with Mockito
- Dockerizing Spring Boot applications

- Hands-on experience with Spring Boot REST APIs
- MongoDB integration using Spring Data
- API documentation using Swagger
- Unit testing with Mockito
- Dockerizing Spring Boot applications

---

## 👤 Author

**Prathamesh Kumbhar**  
Java Backend Developer

---

## ⭐ Final Notes

This project was built as a **personal learning initiative** to strengthen backend development skills using Spring Boot and modern tools. It serves as a solid foundation for building scalable REST APIs and understanding real-world backend workflows.

Feel free to ⭐ star the repository or explore the code!

