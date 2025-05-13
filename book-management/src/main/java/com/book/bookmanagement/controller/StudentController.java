package com.book.bookmanagement.controller;

import com.book.bookmanagement.model.Book;
import com.book.bookmanagement.model.BookRequest;
import com.book.bookmanagement.model.User;
import com.book.bookmanagement.service.BookRequestService;
import com.book.bookmanagement.service.BookService;
import com.book.bookmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookRequestService bookRequestService;

    // Student dashboard
    @GetMapping("/dashboard")
    public String dashboard() {
        return "student-dashboard";
    }

    // List available books
    @GetMapping("/books")
    public String listAvailableBooks(Model model) {
        List<Book> books = bookService.getAvailableBooks();
        model.addAttribute("books", books);
        return "student-books";
    }

    // Request a book (show form)
    @PostMapping("/request")
    public String requestBook(@RequestParam Long bookId, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        bookRequestService.requestBook(bookId, username);
        return "redirect:/student/requests";
    }

    // View student's own book requests
    @GetMapping("/requests")
    public String viewMyRequests(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        User student = userService.findByUsername(username).orElseThrow();
        List<BookRequest> requests = bookRequestService.getRequestsByStudent(student);
        model.addAttribute("requests", requests);
        return "student-requests";
    }

    // Optional: handle errors gracefully
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error-page";
    }
}
