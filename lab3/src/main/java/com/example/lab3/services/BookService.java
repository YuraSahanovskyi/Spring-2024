package com.example.lab3.services;

import com.example.lab3.models.Book;
import com.example.lab3.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public Collection<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Collection<Book> findByName(String name) {
        return bookRepository.findAll().stream()
               .filter(book -> book.getName().toLowerCase().contains(name.toLowerCase()))
               .toList();
    }
    public Collection<Book> findByAuthor(String author) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .toList();
    }
    public Collection<Book> findByKeyword(String keyword) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getKeywords().contains(keyword.toLowerCase()))
                .toList();
    }
    public boolean create(Book book) {
        if (book != null){
            return bookRepository.save(book);
        } else {
            return false;
        }
    }
    public boolean delete(int id) {
           return bookRepository.remove(id);
    }
    public boolean update(int id, String name, String newAuthor, Collection<String> newKeywords) {
        return bookRepository.modify(id, name, newAuthor, newKeywords);
    }
}