package com.example.lab4.utils;

import com.example.lab4.models.Book;
import org.springframework.stereotype.Component;

@Component
public class BookValidator {

    public boolean isValid(Book book) {
        return book != null &&
                book.getName() != null &&
                book.getAuthor() != null &&
                book.getKeywords() != null;
    }
}