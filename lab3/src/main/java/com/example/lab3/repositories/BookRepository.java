package com.example.lab3.repositories;

import com.example.lab3.models.Book;
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

    public boolean save(Book book) {
        if (books.stream().anyMatch(bookInList -> bookInList.getId() == book.getId())) {
            return false;
        }
        return books.add(book);
    }

    public boolean remove(int id) {
        return books.removeIf(book -> book.getId() == id);
    }


    public boolean modify(int id, String name, String newAuthor, Collection<String> newKeywords) {
        Optional<Book> bookOptional = books.stream()
                .filter(book -> book.getId() == id)
                .findFirst();

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setName(name);
            book.setAuthor(newAuthor);
            book.setKeywords(newKeywords);
            books.remove(book.getId());
            books.add(book);
            return true;
        }
        return false;
    }
}
