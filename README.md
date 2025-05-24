# 📚 Book Management System

A web-based Book Management System built using **Spring Boot**, **MySQL**, and **JSP**. This application allows administrators to manage books and users to view available books, search by title or author, and handle basic book operations.

---

## 🚀 Features

- 📘 **Add / Update / Delete Books**
- 🔍 **Search Books by Title or Author**
- 👥 **User Authentication** using Spring Security + BCryptPasswordEncoder
- 🗂️ **Role-based Access Control** (Admin / User)
- 📊 **Dashboard** with book statistics
- 🗄️ **Persistent Data** stored in MySQL
- ✅ **Login & Authentication** with hashed passwords
- 📑 **Data Validation** on both frontend and backend

---

## 🧑‍💻 Technologies Used

- Java (Spring Boot, Servlets, JSP)
- Spring Security
- MySQL (JDBC)
- Apache Tomcat 4.6
- IntelliJ IDEA / Eclipse
- HTML / CSS

---

## 🗃️ Database Schema

### Main Tables

- `users(user_id, username, password, role)`
- `books(book_id, title, author, category, isbn, quantity)`
- `transactions(tx_id, user_id, book_id, issue_date, return_date)` _(optional for issuing/returning)_

---

## 🔐 Default Credentials

| Role  | Username | Password  |
|-------|----------|-----------|
| Admin | admin    | admin123  |
| User  | user     | user123   |

> Passwords are hashed using `BCryptPasswordEncoder`.

---

## 📦 Installation Guide

### 1. Clone the Repository

git clone https://github.com/your-username/book-management-system.git
cd book-management-system


---

### 2. MySQL Configuration

#### ➤ Create the Database

Open your MySQL command-line client or a GUI like phpMyAdmin and run:

CREATE DATABASE bookdb;

#### ➤ Update `application.properties`

Navigate to:  
`src/main/resources/application.properties`  

and add the following configuration:
# code  
spring.datasource.url=jdbc:mysql://localhost:3306/bookdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

> ⚠️ **Note:** Replace `yourpassword` with your actual MySQL password.

#### ➤ Ensure MySQL Connector is Included

Make sure your `pom.xml` includes the MySQL JDBC Driver:

<dependency> <groupId>mysql</groupId> <artifactId>mysql-connector-java</artifactId> <scope>runtime</scope> </dependency> ```

3. Run the Application
Start the Spring Boot application by running:

./mvnw spring-boot:run

Once the application starts, Hibernate will automatically create the required tables in your bookdb MySQL database.

Ready to contribute?
Check out our CONTRIBUTING.md for guidelines and best practices.

