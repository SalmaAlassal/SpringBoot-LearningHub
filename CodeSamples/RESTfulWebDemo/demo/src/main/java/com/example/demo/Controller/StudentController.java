package com.example.demo.Controller;

import com.example.demo.Model.Student;
import com.example.demo.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


// This annotation is used to send the data as a response. If it is not used, then the data will be sent as a view name.
// It's add the @ResponseBody annotation to every method in this class.
@RestController
public class StudentController {

    @Autowired
    StudentRepo repo;

    // When you use @RestController, it assumes that the return value of the method should be serialized and sent as a response data, not as a view name.
    // If you want to send the data as a view name, then you should use @Controller instead of @RestController.
    // So, the student.html file will be sent as a response. It will not be rendered.
    @RequestMapping("/")
    public String student() {
        return "student.html";
    }

    @GetMapping("/students")
    // This returns the data in the form of JSON.
    // Jackson is a library used to convert Java objects to JSON and vice versa. It is used by default in Spring Boot.
    public List<Student> getStudents() {
        return (List<Student>) repo.findAll();
    }

    // This annotation is used to map a POST request to a specific path.
    // consumes = {"application/json", "application/xml"} : This tells Spring to accept only JSON or XML data.
    @PostMapping(path = "/student", consumes = {"application/json", "application/xml"})
    // When a client sends a POST request to the path "/addStudent," the request body contains data representing a Student object in JSON or XML format.
    // The @RequestBody annotation tells Spring to parse the request body and convert it into a Student object automatically, based on the class's structure.
    public Student addStudent(@RequestBody Student student) {
        repo.save(student);
        return student;
    }

    @DeleteMapping("/student/{sid}")
    public String deleteStudent(@PathVariable int sid) {
       Student s = repo.findById(sid).orElse(new Student());
       repo.delete(s);
       return "Deleted";
    }

    // If the data is not present in the database, then it will be added. If it is present, then it will be updated.
    @PutMapping(path = "/student", consumes = {"application/json", "application/xml"})
    public Student saveOrUpdateStudent(@RequestBody Student student) {
        repo.save(student);
        return student;
    }
    
    // Get the student by id using the url : http://localhost:8080/student/1
    @RequestMapping("/student/{sid}")
    public Optional<Student> getStudentById(@PathVariable int sid) {
        return repo.findById(sid);
    }
}