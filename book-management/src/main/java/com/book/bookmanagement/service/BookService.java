package com.book.bookmanagement.service;

import com.book.bookmanagement.model.Book;
import com.book.bookmanagement.model.BookStatus;
import com.book.bookmanagement.model.User;
import com.book.bookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get a book by ID
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // Add a new book
    public Book addBook(Book book) {
        book.setStatus(BookStatus.AVAILABLE);
        book.setIssuedTo(null);
        return bookRepository.save(book);
    }

    // Update an existing book
    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setDescription(updatedBook.getDescription());
            // Optionally allow admin to update status/issuedTo if needed
            return bookRepository.save(book);
        }).orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + id));
    }

    // Delete a book
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Mark a book as issued (used in admin approval)
    public void markAsIssued(Book book, User student) {
        book.setStatus(BookStatus.ISSUED);
        book.setIssuedTo(student);
        bookRepository.save(book);
    }

    // Mark a book as returned (optional future feature)
    public void markAsReturned(Book book) {
        book.setStatus(BookStatus.AVAILABLE);
        book.setIssuedTo(null);
        bookRepository.save(book);
    }

    // Get all available books
    public List<Book> getAvailableBooks() {
        return bookRepository.findByStatus(BookStatus.AVAILABLE);
    }

    // Get all books issued to a specific student
    public List<Book> getBooksIssuedToStudent(User student) {
        return bookRepository.findByIssuedTo(student);
    }
}
