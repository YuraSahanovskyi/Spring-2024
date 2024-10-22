package com.example.lab4.models;

import java.util.Collection;

public class Book {

    public Book(int id, String name, String author, Collection<String> keywords) {
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

    private Collection<String> keywords;

    public Collection<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Collection<String> keywords) {
        this.keywords = keywords;
    }
}
