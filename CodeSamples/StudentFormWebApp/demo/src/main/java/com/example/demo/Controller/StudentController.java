package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Repo.StudentRepo;
import com.example.demo.Model.Student;

@Controller
public class StudentController {

    @Autowired
    StudentRepo repo;

    @RequestMapping("/")
    public String student() {
        return "student.html";
    }

    @RequestMapping("/addStudent")
    public String addStudent(Student student) {
        repo.save(student);
        return "student.html";
    }

    @RequestMapping("/getStudent")
    public ModelAndView getStudent(@RequestParam int id) {
        ModelAndView mv = new ModelAndView("getStudent");
        Student student = repo.findById(id).orElse(new Student());
        mv.addObject("student", student);
        return mv;
    }
}