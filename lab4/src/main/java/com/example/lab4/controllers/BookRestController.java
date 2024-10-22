package com.example.lab4.controllers;

import com.example.lab4.models.Book;
import com.example.lab4.services.BookService;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;


@RestController
@RequestMapping("/api/v1/books")
public class BookRestController {
    private final BookService bookService;
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping()
    Collection<Book> books(@RequestParam(required = false) String name,
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
        return books;
    }
    @PostMapping()
    Book addBook(@RequestBody Book book) {
        return bookService.create(book);
    }
    @PutMapping("/{id}")
    Book updateBook(@PathVariable int id, @RequestBody Book book) {
        book.setId(id);
        return bookService.update(book);
    }
    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable int id) {
        bookService.delete(id);
    }
}
