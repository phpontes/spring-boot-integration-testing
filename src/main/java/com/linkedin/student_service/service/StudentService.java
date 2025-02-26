package com.linkedin.student_service.service;

import com.linkedin.student_service.entity.Student;
import com.linkedin.student_service.exceptions.StudentNotFoundException;
import com.linkedin.student_service.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Cacheable("students")
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }
}
