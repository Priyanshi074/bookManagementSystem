# 📚 Book Management System

A web-based Book Management System built using **Spring Boot**, **MySQL**, and **JSP**. This application allows administrators to manage books and users to view available books, search by title or author, and handle basic book operations.

---

## 🚀 Features

- 📘 **Add / Update / Delete Books**
- 🔍 **Search Books by Title or Author**
- 👥 **User Authentication** (Spring Security + BCryptPasswordEncoder)
- 🗂️ **Role-based Access Control** (Admin / User)
- 📊 **Dashboard** with book statistics
- 🗄️ **Persistent Data** stored in MySQL

---

## 🛠️ Technology Stack

| Layer              | Technology Used                        |
|-------------------|----------------------------------------|
| Backend            | Spring Boot, Spring MVC, Spring Data JPA |
| Frontend           | JSP, HTML5, CSS3, Bootstrap 4          |
| Database           | MySQL                                  |
| ORM                | Hibernate (via Spring Data JPA)        |
| Authentication     | Spring Security, BCryptPasswordEncoder |
| Build Tool         | Maven                                  |
| IDE                | IntelliJ IDEA / Eclipse                |
| Server             | Apache Tomcat (embedded in Spring Boot) |

---

## 🧩 Modules

- **Login & Authentication**: Secure login with hashed passwords
- **Book Management**: Admins can add/edit/delete books
- **Book Browsing**: Users can view and search books
- **User Roles**: Admin and general user separation
- **Data Validation**: On both frontend and backend

---

## 🗃️ Database Schema

**Main Tables:**
- `users(user_id, username, password, role)`
- `books(book_id, title, author, category, isbn, quantity)`
- `transactions(tx_id, user_id, book_id, issue_date, return_date)` (optional for issuing/returning)

---

## 🔐 Default Credentials

| Role  | Username | Password  |
|-------|----------|-----------|
| Admin | admin    | admin123  |
| User  | user     | user123   |

> Passwords are hashed using `BCryptPasswordEncoder`.

---

## 📦 Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/book-management-system.git
   cd book-management-system
