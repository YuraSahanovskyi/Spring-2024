package com.example.lab4.services;

import com.example.lab4.exeptions.BookNotValidException;
import com.example.lab4.models.Book;
import com.example.lab4.repositories.BookRepository;
import com.example.lab4.utils.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
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

    public Collection<Book> getBooks(String name, String author, String keyword) {
        Collection<Book> books = bookRepository.findAll();
        if (name != null && !name.isEmpty()) {
            books = books.stream()
                    .filter(book -> book.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        } if (author != null && !author.isEmpty()) {
            books = books.stream()
                    .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                    .toList();
        } if (keyword != null && !keyword.isEmpty()) {
            books = books.stream()
                    .filter(book -> book.getKeywords().contains(keyword.toLowerCase()))
                    .toList();
        }
        return books;
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

    public Book create(Book book) {
        if (!bookValidator.isValid(book))
            throw new BookNotValidException(book.toString());

        return bookRepository.save(book);
    }

    public void delete(int id) {
        bookRepository.remove(id);
    }

    public Book update(Book book) {
        if (!bookValidator.isValid(book)) {
            throw new BookNotValidException(book.toString());
        }
        return bookRepository.modify(book);
    }
    public Collection<Book> paginate(Collection<Book> books, int page, int size) {
        return books.stream()
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
    }


}
