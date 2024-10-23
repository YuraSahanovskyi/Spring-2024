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
                           @RequestParam(required = false) String keyword,
                           @RequestParam(required = false, defaultValue = "3") int size,
                           @RequestParam(required = false, defaultValue = "0") int page) {
        Collection<Book> books = bookService.getBooks(name, author, keyword);
        return bookService.paginate(books, page, size);
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
