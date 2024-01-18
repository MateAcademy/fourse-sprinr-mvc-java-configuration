package ua.klunniy.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.klunniy.spring.models.Book;
import ua.klunniy.spring.models.BookRowMapper;

import java.sql.*;
import java.util.List;

/**
 * @author Serhii Klunniy
 */
@Component
public class BookDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * from Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * from Book where id=?", new Object[]{id}, new BookRowMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT into Book(name, description) VALUES (?,?)",
                book.getName(), book.getDescription());
    }

    public void update(int id, Book updateBook) {
        jdbcTemplate.update("UPDATE Book SET name=?, description=? where id=?",
                updateBook.getName(), updateBook.getDescription(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE from Book where id=?", id);
    }

}
