package com.example.lab5.repositories;

import com.example.lab5.models.Book;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Repository
public class BookRepository {


    private final DataSource dataSource;
    private final Connection connection;

    public BookRepository(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        connection = dataSource.getConnection();
    }

    public int create(Book book)  {
        try {
            String sql = "INSERT INTO books (name, author, keywords) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, book.getName());
                stmt.setString(2, book.getAuthor());
                stmt.setString(3, String.join(",", book.getKeywords()));
                stmt.executeUpdate();

                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
            return -1;}
        catch (SQLException e){
            return -1;
        }
    }

    public Book read(int id) {
        try{
            String sql = "SELECT * FROM books WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Book(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("author"),
                                Arrays.asList(rs.getString("keywords").split(","))
                        );
                    }
                }
            }
            return null;
        }
        catch (SQLException e){
            return null;
        }
    }
    public boolean update(Book book)  {
        try {
            String sql = "UPDATE books SET name = ?, author = ?, keywords = ? WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, book.getName());
                stmt.setString(2, book.getAuthor());
                stmt.setString(3, String.join(",", book.getKeywords()));
                stmt.setInt(4, book.getId());
                return stmt.executeUpdate() > 0;
            }
        }
        catch (SQLException e){
            return false;
        }
    }


    public boolean delete(int id)  {
        try{
            String sql = "DELETE FROM books WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                return stmt.executeUpdate() > 0;
            }
        }
        catch (SQLException e){
            return false;
        }
    }


    public List<Book> findByAuthor(String author) {
        try{
            String sql = "SELECT * FROM books WHERE author = ?";
            List<Book> result = new ArrayList<>();
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, author);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        result.add(new Book(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("author"),
                                Arrays.asList(rs.getString("keywords").split(","))
                        ));
                    }
                }
            }
            return result;
        }
        catch (SQLException e){
            return null;
        }
    }


    public List<Book> findByKeyword(String keyword)  {
        try{
            String sql = "SELECT * FROM books WHERE keywords LIKE ?";
            List<Book> result = new ArrayList<>();
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, "%" + keyword + "%");
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        result.add(new Book(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("author"),
                                Arrays.asList(rs.getString("keywords").split(","))
                        ));
                    }
                }
            }
            return result;
        }
        catch (SQLException e){
            return null;
        }
    }

    public List<Book> findAll()  {
        try{
            String sql = "SELECT * FROM books";
            List<Book> result = new ArrayList<>();
            try (PreparedStatement stmt = connection.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(new Book(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("author"),
                            Arrays.asList(rs.getString("keywords").split(","))
                    ));
                }
            }
            return result;
            }
        catch (SQLException e){
            return null;
        }
    }
}
