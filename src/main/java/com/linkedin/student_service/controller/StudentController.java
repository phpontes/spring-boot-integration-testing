package com.linkedin.student_service.controller;

import com.linkedin.student_service.exceptions.StudentNotFoundException;
import com.linkedin.student_service.service.StudentService;
import com.linkedin.student_service.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students/{id}")
    Student getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void studentNotFoundException(StudentNotFoundException e) {

    }
}
