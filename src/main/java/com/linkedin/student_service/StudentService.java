package com.linkedin.student_service;

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
        return studentRepository.findById(id).orElse(null);
    }
}
