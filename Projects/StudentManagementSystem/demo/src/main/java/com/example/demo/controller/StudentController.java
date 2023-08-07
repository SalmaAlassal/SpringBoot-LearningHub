package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    // When the user goes to http://localhost:8080/students, this method will be called.
    // Spring Boot automatically creates a model and passes it to the method.
    // The method adds the list of students to the model.
    // Spring Boot looks for a view called students.html.
    // Spring Boot will transfer the data in the model to the view.
    @GetMapping("/students")
    public String getStudents(Model model) {
        // the attribute name "students" is used in students.html
        model.addAttribute("students", studentService.getStudents());
        return "students";
    }

    // @GetMapping is used instead of @PostMapping because this method is displaying a form not submitting one.
    @GetMapping("/students/add")
    public String addStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "add_student";
    }

    // @ModelAttribute is used to bind the form data to the Student object.
    // So you're telling the method to take the form data and put it in the Student object.
    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        // redirect is used to prevent duplicate submissions as well as to prevent the user from refreshing the page and submitting the form again.
        return "redirect:/students";
    }

    @GetMapping("/students/update/{id}")
    public String updateStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "update_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student) {
        studentService.updateStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}