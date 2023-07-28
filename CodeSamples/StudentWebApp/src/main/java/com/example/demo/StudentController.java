package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

// This annotation indicates that the class is a "Controller" class.
@Controller
public class StudentController {
    @RequestMapping("/")
    public ModelAndView student(Student student) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("obj", student);
        mv.setViewName("student");
        return mv;
    }
}