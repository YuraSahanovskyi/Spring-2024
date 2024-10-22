package com.example.lab4.controllers;

import com.example.lab4.models.Book;
import com.example.lab4.services.BookService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.Collection;


@Controller
public class BookController {

    private final ObjectFactory<BookService> bookServiceFactory;

    public BookController(ObjectFactory<BookService> bookServiceFactory) {
        this.bookServiceFactory = bookServiceFactory;
    }

    @GetMapping("/books")
    public String viewBooks(Model model,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String author,
                            @RequestParam(required = false) String keyword) {
        BookService bookService = bookServiceFactory.getObject();
        Collection<Book> books;
        if (name != null && !name.isEmpty()) {
            books = bookService.findByName(name);
        } else if (author != null && !author.isEmpty()) {
            books = bookService.findByAuthor(author);
        } else if (keyword != null && !keyword.isEmpty()) {
            books = bookService.findByKeyword(keyword);
        } else {
            books = bookService.getAllBooks();
        }
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/books/admin")
    public String adminPanel(Model model,
                             @RequestParam(required = false) String message) {
        BookService bookService = bookServiceFactory.getObject();
        model.addAttribute("message",message);
        model.addAttribute("books", bookService.getAllBooks());
        return "admin";
    }

    @PostMapping("/books/admin/create")
    public String createBook(@ModelAttribute Book book) {
        BookService bookService = bookServiceFactory.getObject();
        if (bookService.create(book)) {
            return "redirect:/books";
        } else {
            return "redirect:/books/admin?message=create-error";
        }
    }

    @PostMapping("/books/admin/update")
    public String modifyBook(@ModelAttribute Book book) {
        BookService bookService = bookServiceFactory.getObject();
        if (bookService.update(book)) {
            return "redirect:/books";
        } else {
            return "redirect:/books/admin?message=update-error";
        }
    }

    @PostMapping("/books/admin/delete")
    public String deleteBook(@RequestParam int id) {
        BookService bookService = bookServiceFactory.getObject();
        if (bookService.delete(id)) {
            return "redirect:/books";
        } else {
            return "redirect:/books/admin?message=delete-error";
        }
    }
}
