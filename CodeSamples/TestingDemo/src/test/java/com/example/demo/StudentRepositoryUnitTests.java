package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentRepositoryUnitTests {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    @DisplayName("Test Create Student")
    @Order(1)
//    @Rollback(false)
    public void testCreateStudent() {
        Student student = Student.builder()
                .firstName("Salma")
                .lastName("Ayman")
                .email("Test@example.com")
                .build();

        studentRepository.save(student);

        Assertions.assertNotNull(student.getId());
    }

    @Test
    @Order(2)
    @DisplayName("Get Student by ID")
    public void testGetStudentById() {
        Student student = studentRepository.findById(1L).get();

        System.out.println(student);
        Assertions.assertNotNull(student);
    }

    @Test
    @Order(3)
    @DisplayName("Test Update Student")
    public void testUpdateStudent() {
        Student student = studentRepository.findById(1L).get();
        student.setEmail("xx@test.com");

        studentRepository.save(student);

        Student updatedStudent = studentRepository.findById(1L).get();

        Assertions.assertEquals("xx@test.com", updatedStudent.getEmail());
    }

    @Test
    @Order(4)
    @DisplayName("Test Delete Student")
    public void testDeleteStudent() {
        studentRepository.deleteById(1L);

        Student student = studentRepository.findById(1L).orElse(null);

        Assertions.assertNull(student);
    }
}