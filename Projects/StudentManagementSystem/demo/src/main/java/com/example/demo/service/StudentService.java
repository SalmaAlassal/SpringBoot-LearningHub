package com.example.demo.service;

import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.StudentDao;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    public List<Student> getStudents() {
        return studentDao.findAll();
    }

    public Student saveStudent(Student student) {
        return studentDao.save(student);
    }

    public Student updateStudent(Student student) {
        return studentDao.save(student);
    }

    public Student getStudentById(Long id) {
        // The Optional class is a container that may or may not contain a non-null value.
        // findById returns an Optional<Student> object, so we need to call get() to get the actual Student object
        // By calling .get() on the Optional object, you are extracting the actual Student object from within the Optional if it exists.
        // If there's no value inside the Optional, a NoSuchElementException will be thrown.
        return studentDao.findById(id).get();
    }

    public void deleteStudent(Long id) {
        studentDao.deleteById(id);
    }
}
