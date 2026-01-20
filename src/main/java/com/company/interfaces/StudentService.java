package com.company.interfaces;

import com.company.dto.StudentDTO;
import java.util.List;

public interface StudentService {
    void addStudent(StudentDTO student);
    List<StudentDTO> getAllStudents();
    List<StudentDTO> findStudent(String name, String surname, Integer age);
}
