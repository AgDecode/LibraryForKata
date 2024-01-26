package com.alex.spring.mvc.util;

import com.alex.spring.mvc.DAO.BookDAO;
import com.alex.spring.mvc.DAO.PersonDAO;
import com.alex.spring.mvc.models.Book;
import com.alex.spring.mvc.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    @Autowired
    private BookDAO bookDAO;


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        if (book.getBookName().isEmpty()){
            errors.rejectValue("bookName", "1", "У книги должно быть название");
        }
        if (book.getAuthor().isEmpty()){
            errors.rejectValue("author", "1", "У книги должен быть автор");
        }
        if (book.getBookYear() < 1500){
            errors.rejectValue("bookYear", "1", "У книги должен быть год публикации не меньше 1500г");
        }
        if (book.getBookYear() > 2024){
            errors.rejectValue("bookYear", "1", "У книги должен быть год публикации не больше 2024");
        }
    }



}
