package com.linkedin.student_service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
public class StudentServiceTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @DisplayName("Returning saved student from service layer")
    @Test
    void getStudentById_forSavedStudent_isReturned() {

        //given
        Student savedStudent = studentRepository.save(new Student(null, "Mark"));

        //when
        Student student = studentService.getStudentById(savedStudent.getId());

        //then
        then(student.getName()).isEqualTo("Mark");
        then(student.getId()).isNotNull();
    }
}
