package com.book.bookmanagement.service;

import com.book.bookmanagement.model.Book;
import com.book.bookmanagement.model.BookRequest;
import com.book.bookmanagement.model.BookStatus;
import com.book.bookmanagement.model.User;
import com.book.bookmanagement.repository.BookRepository;
import com.book.bookmanagement.repository.BookRequestRepository;
import com.book.bookmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookRequestService {

    @Autowired
    private BookRequestRepository bookRequestRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    // Student requests a book
    public BookRequest requestBook(Long bookId, String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (userOptional.isPresent() && bookOptional.isPresent()) {
            Book book = bookOptional.get();
            User student = userOptional.get();

            if (book.getStatus() != BookStatus.AVAILABLE) {
                throw new IllegalStateException("Book is not available for request.");
            }

            BookRequest request = new BookRequest(student, book, false); // Or use status PENDING if you have an enum
            book.setStatus(BookStatus.REQUESTED);
            bookRepository.save(book);

            return bookRequestRepository.save(request);
        } else {
            throw new IllegalArgumentException("Invalid user or book ID.");
        }
    }

    // Admin approves a book request
    public BookRequest approveRequest(Long requestId) {
        BookRequest request = bookRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid request ID."));

        if (!request.isApproved()) {
            request.setApproved(true);

            // Update book status and issuedTo
            Book book = request.getBook();
            book.setStatus(BookStatus.ISSUED);
            book.setIssuedTo(request.getStudent());
            bookRepository.save(book);
        }

        return bookRequestRepository.save(request);
    }

    // Admin rejects a book request
    public BookRequest rejectRequest(Long requestId) {
        BookRequest request = bookRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid request ID."));

        if (!request.isApproved()) {
            // Optionally set a rejected status if you have it
            Book book = request.getBook();
            book.setStatus(BookStatus.AVAILABLE);
            bookRepository.save(book);
            // Optionally, delete the request or mark as rejected
        }

        return bookRequestRepository.save(request);
    }

    // Admin gets all pending requests
    public List<BookRequest> getAllPendingRequests() {
        return bookRequestRepository.findByApproved(false);
    }

    // Admin gets all approved requests
    public List<BookRequest> getAllApprovedRequests() {
        return bookRequestRepository.findByApproved(true);
    }

    // Student gets their own requests
    public List<BookRequest> getRequestsByStudent(User student) {
        return bookRequestRepository.findByStudent(student);
    }
}
