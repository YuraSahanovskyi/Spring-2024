package com.example.lab5.services;

import com.example.lab5.models.Book;
import com.example.lab5.repositories.BookRepository;
import com.example.lab5.utils.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

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

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book get(int id){
        return bookRepository.read(id);
    }



    public List<Book> findByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findByKeyword(String keyword) {
        return bookRepository.findByKeyword(keyword);
    }

    public int create(Book book) {
        if (!bookValidator.isValid(book))
            return -1;

        return bookRepository.create(book);
    }

    public boolean delete(int id) {
        return bookRepository.delete(id);
    }

    public boolean update(Book book) {
        if (!bookValidator.isValid(book))
            return false;

        return bookRepository.update(book);
    }


}
