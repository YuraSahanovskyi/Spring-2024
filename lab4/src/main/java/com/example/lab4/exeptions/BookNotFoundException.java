package com.example.lab4.exeptions;

public class BookNotFoundException extends IllegalArgumentException {
    public BookNotFoundException(String message) {
        super("Book not found: " + message);
    }
}
