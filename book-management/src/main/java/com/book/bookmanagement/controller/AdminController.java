package com.book.bookmanagement.controller;

import com.book.bookmanagement.model.Book;
import com.book.bookmanagement.model.BookRequest;
import com.book.bookmanagement.service.BookRequestService;
import com.book.bookmanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRequestService bookRequestService;

    // Admin home page
    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin-dashboard";
    }

    // List all books
    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "admin-books";
    }

    // Show form to add a new book
    @GetMapping("/books/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    // Process add book form
    @PostMapping("/books/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/admin/books";
    }

    // Show form to update a book
    @GetMapping("/books/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));
        model.addAttribute("book", book);
        return "edit-book";
    }

    // Process update book form
    @PostMapping("/books/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        bookService.updateBook(id, book);
        return "redirect:/admin/books";
    }

    // Delete a book
    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/admin/books";
    }

    // View all pending book requests
    @GetMapping("/requests")
    public String viewPendingRequests(Model model) {
        List<BookRequest> pendingRequests = bookRequestService.getAllPendingRequests();
        model.addAttribute("requests", pendingRequests);
        return "book-requests";
    }

    // Approve a book request
    @GetMapping("/requests/approve/{id}")
    public String approveRequest(@PathVariable Long id) {
        bookRequestService.approveRequest(id);
        return "redirect:/admin/requests";
    }

    // Reject a book request
    @GetMapping("/requests/reject/{id}")
    public String rejectRequest(@PathVariable Long id) {
        bookRequestService.rejectRequest(id);
        return "redirect:/admin/requests";
    }

    // Optional: handle errors gracefully
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error-page"; // Create an error-page.html template
    }
}
