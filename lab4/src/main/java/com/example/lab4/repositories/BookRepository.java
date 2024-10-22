package com.example.lab4.repositories;

import com.example.lab4.models.Book;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepository {

    private final List<Book> books;

    public BookRepository() {
        // Initialize with some fake books
        books = new ArrayList<>();
        books.add(new Book(1,"1984", "George Orwell", Arrays.asList("dystopian", "political fiction", "science fiction")));
        books.add(new Book(2,"To Kill a Mockingbird", "Harper Lee", Arrays.asList("classic", "race", "justice")));
        books.add(new Book(3, "The Great Gatsby", "F. Scott Fitzgerald", Arrays.asList("classic", "American literature", "tragedy")));
        books.add(new Book(4,"Moby Dick", "Herman Melville", Arrays.asList("adventure", "classic", "whaling")));
        books.add(new Book(5,"The Catcher in the Rye", "J.D. Salinger", Arrays.asList("classic", "coming of age", "alienation")));
    }

    public Collection<Book> findAll() {
        return books;
    }

    public Book save(Book book) {
        if (books.stream().anyMatch(bookInList -> bookInList.getId() == book.getId())) {
            throw new IllegalArgumentException("Book already exists");
        }
        books.add(book);
        return book;
    }

    public void remove(int id) {
        books.removeIf(book -> book.getId() == id);
    }


    public Book modify(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        Optional<Book> oldBook = books.stream().filter(bookInList -> bookInList.getId() == book.getId()).findFirst();
        if(oldBook.isPresent()) {
            books.remove(oldBook.get());
            books.add(book);
            return book;
        } else {
            throw new IllegalArgumentException("Book not found");
        }
    }
}
