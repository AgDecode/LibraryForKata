package com.alex.spring.mvc.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    private String bookName;
    private String author;
    private int bookYear;
    private Person owner;


    public void book(){}

    public void book(int id, String bookName, String author, int bookYear){
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.bookYear = bookYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public int getBookYear() {
        return bookYear;
    }

    public void setBookYear(int bookYear) {
        this.bookYear = bookYear;
    }
}
