package com.example.lab4.services;

import com.example.lab4.models.Book;
import com.example.lab4.repositories.BookRepository;
import com.example.lab4.utils.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Scope("prototype")
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    private BookValidator bookValidator;

    @Autowired
    public void setBookValidator(BookValidator bookValidator) {
        this.bookValidator = bookValidator;
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
        if (!bookValidator.isValid(book))
            return false;

        return bookRepository.save(book);
    }

    public boolean delete(int id) {
        return bookRepository.remove(id);
    }

    public boolean update(Book book) {
        if (!bookValidator.isValid(book))
            return false;

        return bookRepository.modify(book);
    }


}
