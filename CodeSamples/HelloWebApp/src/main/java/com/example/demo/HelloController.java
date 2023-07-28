package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// This annotation indicates that the class is a "Controller" class.
@Controller
public class HelloController {
    // This annotation maps HTTP GET requests onto specific handler methods.
    // In this case, the method will only be invoked when the root path is accessed.
    @GetMapping("/")
    // This annotation indicates that the return value of the method should be bound to the web response body.
    @ResponseBody
    public String index() {
        return "Hello from Spring Boot!";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
//    @GetMapping("/hello")
    public String hello() {
//        return "/pages/hello.html";
        return "hello"; // This will return the hello.html webpage.
    }
}