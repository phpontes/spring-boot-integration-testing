package com.linkedin.student_service.repository;

import com.linkedin.student_service.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student getStudentByName(String name);

    @Query(value = "select avg (grade) from Student where active=true", nativeQuery = true)
    Double getAvgGradeForActiveStudents();
}
