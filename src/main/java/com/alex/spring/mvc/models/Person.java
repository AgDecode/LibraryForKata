package com.alex.spring.mvc.models;
public class Person {

    private int id;
    private int yearOfBirthday;
    private String fullName;

    public Person(int id, String fullName, int yearOfBirthday){
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirthday = yearOfBirthday;
    }

    public Person(){

    }
    public int getYearOfBirthday() {
        return yearOfBirthday;
    }

    public void setYearOfBirthday(int YearOfBirthday) {
        this.yearOfBirthday = YearOfBirthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
