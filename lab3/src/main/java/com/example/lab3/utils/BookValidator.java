package com.example.lab3.utils;

import com.example.lab3.models.Book;
import org.springframework.context.annotation.Bean;
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
