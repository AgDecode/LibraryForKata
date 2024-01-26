package com.alex.spring.mvc.util;

import com.alex.spring.mvc.DAO.PersonDAO;
import com.alex.spring.mvc.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    @Autowired
    private PersonDAO personDAO;


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if(personDAO.getBooksByFullName(person.getFullName()).isPresent()){
            errors.rejectValue("fullName", "1","ФИО должно быть уникальным");
        }

        if (person.getFullName().isEmpty()){
            errors.rejectValue("fullName", "1", "Имя не одлжно быть пустое, у вас точно есть имя");
        }

        if (person.getFullName().length() < 3){
            errors.rejectValue("fullName", "1", "Ваше имя должно быть больше 3х символов");
        }
        if (person.getFullName().length() > 100){
            errors.rejectValue("fullName", "1", "Ваше имя должно быть меньше 100 символов");
        }



        if (person.getYearOfBirthday() < 1900){
            errors.rejectValue("yearOfBirthday", "2", "Ваша дата рождения должна быть больше 1900");
        }
        if (person.getYearOfBirthday() > 2024){
            errors.rejectValue("yearOfBirthday", "2", "Ваша дата рождения должна быть меньше 2024, или вы из будующего :)");
        }

    }

}
