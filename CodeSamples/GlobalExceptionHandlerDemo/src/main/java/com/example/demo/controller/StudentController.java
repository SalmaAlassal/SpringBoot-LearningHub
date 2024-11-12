package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    // Get all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Get a student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // Create a new student
    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        return studentService.insertStudent(student);
    }

    // Update an existing student
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    // Delete a student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        return studentService.removeStudentById(id);
    }

    // Enroll a student in a course
    @PostMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<String> enrollStudentInCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        return studentService.enrollStudentInCourse(studentId, courseId);
    }

    // Find students by course name
    @GetMapping("/course")
    public ResponseEntity<List<Student>> findStudentsByCourseName(@RequestParam String courseName) {
        return studentService.findStudentsByCourseName(courseName);
    }

    // Find students by last name
    @GetMapping("/name")
    public ResponseEntity<List<Student>> findStudentsByLastName(@RequestParam String lastName) {
        return studentService.findStudentsByLastName(lastName);
    }
}
