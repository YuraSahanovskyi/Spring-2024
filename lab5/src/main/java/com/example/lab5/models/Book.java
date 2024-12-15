package com.example.lab5.models;

import java.util.Collection;
import java.util.List;

public class Book {

    public Book(int id, String name, String author, List<String> keywords) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.keywords = keywords;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    private List<String> keywords;

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
