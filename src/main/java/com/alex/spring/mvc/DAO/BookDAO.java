package com.alex.spring.mvc.DAO;

import com.alex.spring.mvc.models.Book;
import com.alex.spring.mvc.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getBook(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO book(bookname,author,bookyear) VALUES (?,?,?)",
                book.getBookName(), book.getAuthor(), book.getBookYear());
    }

    public void uppdate(int id, Book uppdateBook){
        jdbcTemplate.update("UPDATE book SET bookname=?, author=?, bookyear=? WHERE id=?",
                uppdateBook.getBookName(), uppdateBook.getAuthor(), uppdateBook.getBookYear(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    public Optional<Person> getBookOwner(int id){
        return jdbcTemplate.query("SELECT person.* FROM book JOIN person ON book.person_id = person.id "
        + "WHERE book.id =?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void release(int id){
        jdbcTemplate.update("UPDATE book SET person_id=NULL WHERE id=?", id);
    }

    public void assign(int id, Person selectedPerson){
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE id=?", selectedPerson.getId(), id);
    }
}
