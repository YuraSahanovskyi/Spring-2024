package com.example.lab6.controllers;

import com.example.lab6.models.Book;
import com.example.lab6.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;


    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return ResponseEntity.ok(service.create(book));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        return ResponseEntity.ok(service.update(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id)  {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> findByAuthor(@PathVariable String author)  {
        return ResponseEntity.ok(service.findByAuthor(author));
    }

    @GetMapping("/keyword/{keyword}")
    public ResponseEntity<List<Book>> findByKeyword(@PathVariable String keyword) {
        return ResponseEntity.ok(service.findByKeyword(keyword));
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAllBooks() {
        return ResponseEntity.ok(service.getAllBooks());
    }
}