package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

    public ResponseEntity<Student> getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student with ID " + id + " not found"));
        return ResponseEntity.ok(student);
    }

    public ResponseEntity<String> insertStudent(Student student) {
        studentRepository.save(student);
        return new ResponseEntity<>("Student created successfully!", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with ID " + id + " not found"));

        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());

        studentRepository.save(existingStudent);
        return ResponseEntity.ok("Student updated successfully!");
    }

    public ResponseEntity<String> removeStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student with ID " + id + " not found");
        }
        studentRepository.deleteById(id);
        return ResponseEntity.ok("Student deleted successfully!");
    }

    public ResponseEntity<String> enrollStudentInCourse(Long studentId, Long courseId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student with ID " + studentId + " not found");
        }
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course with ID " + courseId + " not found");
        }

        Student student = studentRepository.findById(studentId).get();
        student.getCourses().add(courseRepository.findById(courseId).get());
        studentRepository.save(student);

        return ResponseEntity.ok("Student enrolled successfully in course");
    }

    public ResponseEntity<List<Student>> findStudentsByCourseName(String courseName) {
        List<Student> students = studentRepository.findStudentsByCourseName(courseName);
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(students);
    }

    public ResponseEntity<List<Student>> findStudentsByLastName(String lastName) {
        List<Student> students = studentRepository.findByLastName(lastName);
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(students);
    }
}