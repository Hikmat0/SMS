package com.company.dto;

import java.io.Serializable;

public class StudentDTO implements Serializable {
    private String name;
    private String surname;
    private int age;
    private String email;
    private double averageGrade;


    public StudentDTO(String name, String surname, int age, String email, double averageGrade) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.averageGrade = averageGrade;
    }


    public String getName() { return name; }
    public String getSurname() { return surname; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    public double getAverageGrade() { return averageGrade; }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", averageGrade=" + averageGrade +
                '}';
    }
}
