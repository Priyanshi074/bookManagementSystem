package com.book.bookmanagement.repository;

import com.book.bookmanagement.model.Book;
import com.book.bookmanagement.model.BookStatus;
import com.book.bookmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Find all books by status (e.g., AVAILABLE, REQUESTED, ISSUED)
    List<Book> findByStatus(BookStatus status);

    // Find all books issued to a specific user
    List<Book> findByIssuedTo(User user);

    // Optional: Find all books by author
    List<Book> findByAuthorContainingIgnoreCase(String author);

    // Optional: Find all books by title
    List<Book> findByTitleContainingIgnoreCase(String title);
}
