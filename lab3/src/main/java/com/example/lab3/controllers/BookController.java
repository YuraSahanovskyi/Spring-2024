package com.example.lab3.controllers;

import com.example.lab3.models.Book;
import com.example.lab3.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.Collection;
import java.util.List;


@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String viewBooks(Model model,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String author,
                            @RequestParam(required = false) String keyword) {
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
        if (message != null) {
            switch (message) {
                case "create-error" -> model.addAttribute("message", "Unable to create new book");
                case "delete-error" -> model.addAttribute("message", "Unable to delete book");
                case "update-error" -> model.addAttribute("message", "Unable to update book");
                default -> model.addAttribute("message", message);
            }
        }
        model.addAttribute("books", bookService.getAllBooks());
        return "admin";
    }

    @PostMapping("/books/admin")
    public String createBook(@RequestParam int id,
                             @RequestParam String name,
                             @RequestParam String author,
                             @RequestParam String keywords) {
        if (bookService.create(new Book(id, name, author, List.of(keywords.split(","))))) {
            return "redirect:/books";
        } else {
            return "redirect:/books/admin?create-error";
        }
    }

//    @PutMapping("/books/admin")
//    public String modifyBook(@RequestParam int id,
//                             @RequestParam(required = false) String name,
//                             @RequestParam(required = false) String author,
//                             @RequestParam(required = false) String keywords) {
//        Optional<Book> optionalBook = bookService.getAllBooks().stream().filter(book -> book.getId() == id).findFirst();
//        if (optionalBook.isPresent()) {
//            Book book = optionalBook.get();
//            if (bookService.update(id,
//                    name != null ? name : book.getName(),
//                    author != null ? author : book.getAuthor(),
//                    keywords != null ? List.of(keywords.split(",")) : book.getKeywords())) {
//                return "redirect:/books";
//            } else {
//                return "redirect:/books/admin?update-error";
//            }
//        } else {
//            return "redirect:/books/admin?update-error";
//        }
//    }
//
//    @DeleteMapping("/books/admin")
//    public String deleteBook(@RequestParam int id) {
//        if (bookService.delete(id)) {
//            return "redirect:/books";
//        } else {
//            return "redirect:/books/admin?delete-error";
//        }
//    }
}
