package com.example.lab6.services;

import com.example.lab6.models.Book;
import com.example.lab6.repositories.BookRepository;
import com.example.lab6.utils.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book get(int id){
        return bookRepository.getBookById(id);
    }


    @Transactional(readOnly = true)
    public List<Book> findByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }

    @Transactional(readOnly = true)
    public List<Book> findByKeyword(String keyword) {
        return bookRepository.findByKeyword(keyword);
    }

    @Transactional(propagation = Propagation.NESTED)
    public Book create(Book book) {
        if (!bookValidator.isValid(book))
            return null;

        return bookRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
         bookRepository.deleteById(id);
    }

    @Transactional(propagation = Propagation.NESTED)
    public Book update(Book book) {
        if (!bookValidator.isValid(book))
            return null;

        return bookRepository.save(book);
    }


}
