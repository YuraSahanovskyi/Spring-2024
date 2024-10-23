package com.example.lab4.exeptions;

public class BookAlreadyExistsException extends IllegalArgumentException {
    public BookAlreadyExistsException(String message) {
        super("Book already exists: " + message);
    }
}
