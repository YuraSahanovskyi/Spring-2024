package com.example.lab6.repositories;

import com.example.lab6.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByAuthor(String author);

    default List<Book> findByKeyword(String keyword) {
        return findAll().stream().filter(b -> b.getKeywords().contains(keyword)).collect(Collectors.toList());
    }

    Book getBookById(int id);
}
