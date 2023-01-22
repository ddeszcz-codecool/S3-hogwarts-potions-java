package com.codecool.hogwarts_potions.controller;


import com.codecool.hogwarts_potions.model.Student;
import com.codecool.hogwarts_potions.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/student")
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable("id") Long id) {
        return studentService.getStudentById(id);
    }

}
