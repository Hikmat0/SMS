package com.company.impl;

import com.company.dto.StudentDTO;
import com.company.exceptions.FileStorageException;
import com.company.exceptions.InvalidInputException;
import com.company.exceptions.StudentNotFoundException;
import com.company.interfaces.StudentService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {


    private static final String FILE_NAME = "students.dat";
    private List<StudentDTO> students;


    public StudentServiceImpl() {
        students = loadFromFile();
    }


    @Override
    public void addStudent(StudentDTO student) {
        validateStudent(student);
        students.add(student);
        saveToFile();
    }


    @Override
    public List<StudentDTO> getAllStudents() {
        if (students.isEmpty()) {
            throw new StudentNotFoundException("No students found in system");
        }
        return students;
    }


    @Override
    public List<StudentDTO> findStudent(String name, String surname, Integer age) {
        List<StudentDTO> result = new ArrayList<>();
        for (StudentDTO s : students) {
            boolean match = false;
            if (name != null && surname != null) {
                match = s.getName().equalsIgnoreCase(name)
                        && s.getSurname().equalsIgnoreCase(surname);
            }
            if (age != null) {
                match = match || s.getAge() == age;
            }
            if (match) {
                result.add(s);
            }
        }
        if (result.isEmpty()) {
            throw new StudentNotFoundException("Student not found with given criteria");
        }
        return result;
    }


    private void validateStudent(StudentDTO student) {
        if (student.getName().isEmpty() || student.getSurname().isEmpty()) {
            throw new InvalidInputException("Name and surname cannot be empty");
        }
        if (student.getAge() <= 0) {
            throw new InvalidInputException("Age must be greater than 0");
        }
        if (!student.getEmail().contains("@")) {
            throw new InvalidInputException("Invalid email format");
        }
        if (student.getAverageGrade() < 0 || student.getAverageGrade() > 100) {
            throw new InvalidInputException("Average grade must be between 0 and 100");
        }
    }


    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            throw new FileStorageException("Failed to save students to file");
        }
    }

    @SuppressWarnings("unchecked")
    private List<StudentDTO> loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<StudentDTO>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new FileStorageException("Failed to load students from file");
        }
    }
}

