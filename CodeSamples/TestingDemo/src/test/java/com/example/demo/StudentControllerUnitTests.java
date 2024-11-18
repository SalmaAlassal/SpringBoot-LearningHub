package com.example.demo;

import com.example.demo.controller.StudentController;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.HttpStatus;

@WebMvcTest(StudentController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentControllerUnitTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper; // for converting objects to JSON and vice versa

    private Student student;

    @BeforeEach
    public void setUp() {
        student = Student.builder()
                .id(1L)
                .firstName("Salma")
                .lastName("Ayman")
                .email("test@test.com")
                .build();
    }
//    // Test the POST request
//    @Test
//    @Order(1)
//    public void testCreateStudent() throws Exception {
//        // Mock the service method
//        given(studentService.insertStudent(student))
//                .willReturn(ResponseEntity.status(HttpStatus.CREATED).body("Student created successfully!"));
//
//        // Perform the POST request
//        ResultActions response = mockMvc.perform(post("/students")
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(student)));
//
//        // Debug the response body
//        MvcResult result = response.andReturn();
//        System.out.println("Response Body: " + result.getResponse().getContentAsString());
//
//        // Verify the response
//        response.andExpect(status().isOk()) // Expect 201 Created
//                .andExpect(content().string("Student created successfully!")) // Plain text response
//                .andDo(print());
//    }
//
//    // Test the Put request for updating a student
//    @Test
//    public void testUpdateStudent() throws Exception {
//        // Mock the service method
//        given(studentService.getStudentById(student.getId())).willReturn(ResponseEntity.of(Optional.of(student)));
//        student.setFirstName("Mr Testoo");
//        given(studentService.updateStudent(student.getId(), student)).willReturn(ResponseEntity.ok("Student updated successfully!"));
//
//        // Action
//        ResultActions response = mockMvc.perform(post("/students")
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(student)));
//
//        // Verification
//        response.andExpect(status().isOk())
//                .andExpect(content().string("Student updated successfully!"))
//                .andDo(print());
//
//    }


    @Test
    public void testGetStudentById() throws Exception {
        // Precondition
        given(studentService.getStudentById(student.getId())).willReturn(ResponseEntity.of(Optional.of(student)));

        // Action
        ResultActions response = mockMvc.perform(get ("/students/{id}", student.getId()));

        // Verification
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(student.getId().intValue())))
                .andExpect(jsonPath("$.firstName", is(student.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(student.getLastName())))
                .andExpect(jsonPath("$.email", is(student.getEmail())));
    }

    // Test the GET request for all students
    @Test
    public void testGetAllStudents() throws Exception {
        // Create a list of students
        List<Student> studentsList = new ArrayList<>();
        studentsList.add(student);
        studentsList.add(Student.builder()
                .id(2L)
                .firstName("Amany")
                .lastName("Alassal")
                .email("x@test.com")
                .build());

        // Precondition
        given(studentService.getAllStudents()).willReturn(ResponseEntity.ok().body(studentsList));

        // Action
        ResultActions response = mockMvc.perform(get("/students"));

        // Verification
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(studentsList.size())))
                .andExpect(jsonPath("$[0].id", is(studentsList.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].firstName", is(studentsList.get(0).getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(studentsList.get(0).getLastName())))
                .andExpect(jsonPath("$[0].email", is(studentsList.get(0).getEmail())))
                .andExpect(jsonPath("$[1].id", is(studentsList.get(1).getId().intValue())))
                .andExpect(jsonPath("$[1].firstName", is(studentsList.get(1).getFirstName())))
                .andExpect(jsonPath("$[1].lastName", is(studentsList.get(1).getLastName())))
                .andExpect(jsonPath("$[1].email", is(studentsList.get(1).getEmail())));
    }

    // Test the DELETE request
    @Test
    public void testDeleteStudent() throws Exception {
        // Mock the service method
        given(studentService.removeStudentById(student.getId())).willReturn(ResponseEntity.ok("Student deleted successfully!"));

        // Perform the DELETE request
        ResultActions response = mockMvc.perform(delete("/students/{id}", student.getId()));

        // Verify the response
        response.andExpect(status().isOk())
                .andExpect(content().string("Student deleted successfully!"))
                .andDo(print());
    }
}