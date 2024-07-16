package com.luv2code.demo.entity;

public class Student {
    ////////////////////////////////////////////////////
    // Define fields
    private String firstName;
    private String lastName;
    ////////////////////////////////////////////////////
    // Define constructors
    public Student(){

    }
    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    ////////////////////////////////////////////////////
    // Define getters & setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    ////////////////////////////////////////////////////
}
