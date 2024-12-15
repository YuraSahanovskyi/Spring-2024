package com.example.lab5.controllers;

import com.example.lab5.models.Book;
import com.example.lab5.repositories.BookRepository;
import com.example.lab5.services.BookService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;


    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public int createBook(@RequestBody Book book)  {
        return service.create(book);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return service.get(id);
    }

    @PutMapping
    public boolean updateBook(@RequestBody Book book){
        return service.update(book);
    }

    @DeleteMapping("/{id}")
    public boolean deleteBook(@PathVariable int id)  {
        return service.delete(id);
    }

    @GetMapping("/author/{author}")
    public List<Book> findByAuthor(@PathVariable String author)  {
        return service.findByAuthor(author);
    }

    @GetMapping("/keyword/{keyword}")
    public List<Book> findByKeyword(@PathVariable String keyword) {
        return service.findByKeyword(keyword);
    }

    @GetMapping
    public List<Book> findAllBooks() {
        return service.getAllBooks();
    }
}