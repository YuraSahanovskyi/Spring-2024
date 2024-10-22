package com.example.lab4.exeptions;

public class BookNotValidException extends IllegalArgumentException {
    public BookNotValidException(String message) {
      super("All book's fields must be not null: " + message);
    }
}
