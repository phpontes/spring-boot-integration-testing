package com.linkedin.student_service;

import com.linkedin.student_service.entity.Student;
import com.linkedin.student_service.exceptions.StudentNotFoundException;
import com.linkedin.student_service.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @Test
    void getStudent_forSavedStudent_isReturned() throws Exception {

        //given
        given(studentService.getStudentById(anyLong())).willReturn(
                Student.builder().id(1L).name("Mark").grade(10).build()
        );

        //when //then
        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("name").value("Mark"))
                .andExpect(jsonPath("grade").value(10));
    }

    @Test
    void getStudent_forMissingStudent_status404() throws Exception {

        //given
        given(studentService.getStudentById(anyLong())).willThrow(StudentNotFoundException.class);

        //when //then
        mockMvc.perform(get("/students/1"))
                .andExpect(status().isNotFound());
    }
}
