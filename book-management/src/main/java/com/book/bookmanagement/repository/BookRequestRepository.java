package com.book.bookmanagement.repository;

import com.book.bookmanagement.model.BookRequest;
import com.book.bookmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRequestRepository extends JpaRepository<BookRequest, Long> {

    // Find all requests by approval status
    List<BookRequest> findByApproved(boolean approved);

    // Find all requests for a specific student
    List<BookRequest> findByStudent(User student);

    // Optional: Find all requests for a specific book
    List<BookRequest> findByBookId(Long bookId);

    // Optional: Find pending requests for a specific book
    List<BookRequest> findByBookIdAndApproved(Long bookId, boolean approved);
}
