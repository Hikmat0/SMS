package com.company;

import com.company.controller.StudentController;
import com.company.impl.StudentServiceImpl;
import com.company.interfaces.StudentService;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        StudentService service = new StudentServiceImpl();
        StudentController controller = new StudentController(service);
        controller.start();
    }
}