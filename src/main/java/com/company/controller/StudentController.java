package com.company.controller;

import com.company.dto.StudentDTO;
import com.company.exceptions.InvalidInputException;
import com.company.exceptions.StudentException;
import com.company.interfaces.StudentService;

import java.util.Scanner;

public class StudentController {


    private final StudentService service;
    private final Scanner scanner = new Scanner(System.in);


    public StudentController(StudentService service) {
        this.service = service;
    }


    public void start() throws InterruptedException {
        while (true) {
          //3Thread.sleep(100);
            try {
                System.out.println("\n1. Add Student");
                System.out.println("2. Show All Students");
                System.out.println("3. Find Student");
                System.out.println("0. Exit");
                System.out.print("Select: ");


                int choice = Integer.parseInt(scanner.nextLine());


                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> showAll();
                    case 3 -> findStudent();
                    case 0 -> System.exit(0);
                    default -> throw new InvalidInputException("Invalid menu selection");
                }
            } catch (StudentException e) {
                System.err.println("ERROR: " + e.getMessage());
            }
        }
    }


    private void addStudent() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Surname: ");
        String surname = scanner.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Average Grade: ");
        double avg = Double.parseDouble(scanner.nextLine());


        service.addStudent(new StudentDTO(name, surname, age, email, avg));
        System.out.println("Student added successfully");
    }


    private void showAll() {
        for (StudentDTO s : service.getAllStudents()) {
            System.out.println(s);
        }
    }


    private void findStudent() {
        System.out.println("Search by:\n1. Name & Surname\n2. Age");
        int option = Integer.parseInt(scanner.nextLine());
        if (option == 1) {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Surname: ");
            String surname = scanner.nextLine();
            service.findStudent(name, surname, null).forEach(System.out::println);
        } else if (option == 2) {
            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine());
            service.findStudent(null, null, age).forEach(System.out::println);
        } else {
            throw new InvalidInputException("Invalid search option");
        }
    }
}