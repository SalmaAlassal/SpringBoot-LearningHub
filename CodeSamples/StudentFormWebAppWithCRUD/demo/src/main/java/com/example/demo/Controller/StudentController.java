package com.example.demo.Controller;

import com.example.demo.Model.Student;
import com.example.demo.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class StudentController {

    @Autowired
    StudentRepo repo;

    @RequestMapping("/")
    public String student() {
        return "student.html";
    }

    @RequestMapping("/getStudent")
    public ModelAndView getStudent(@RequestParam int id) {
        ModelAndView mv = new ModelAndView("getStudent");
        Student student = repo.findById(id).orElse(new Student());
        mv.addObject("student", student);

        System.out.println(repo.findByStech("Java"));
        System.out.println(repo.findByStechSorted("Java"));
        System.out.println(repo.findBySidGreaterThan(1));
        return mv;
    }

    // This method is used to reterive all the data from the database.
    @RequestMapping("/students")
    // This annotation is used to send the data as a response. If it is not used, then the data will be sent as a view name.
    @ResponseBody
//    public String getStudents() {
//        return repo.findAll().toString();
//    }

    // This returns the data in the form of JSON.
    // Jackson is a library used to convert Java objects to JSON and vice versa. It is used by default in Spring Boot.
    public List<Student> getStudents() {
        return (List<Student>) repo.findAll();
    }

//    @RequestMapping("/student/{sid}")
//    @ResponseBody
//    public String getStudentById(@PathVariable int sid) {
//        return repo.findById(sid).toString();
//    }

    // Get the student by stech using the url : http://localhost:8080/student/Java
    @RequestMapping("/student/{stech}")
    @ResponseBody
//    public String getStudentById(@PathVariable String stech) { // @PathVariable is used to get the value from the url.
//        return repo.findByStech(stech).toString();
//    }

    // This returns the data in the form of JSON.
    public List<Student> getStudentById(@PathVariable String stech) { // @PathVariable is used to get the value from the url.
        return repo.findByStech(stech);
    }
}
