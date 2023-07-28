package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

// This annotation indicates that the class is a "Controller" class.
@Controller
public class WelcomeController {
    // Method 1: Accepting HttpServletRequest and retrieving parameter manually
    @RequestMapping("/")
    public String welcome(HttpServletRequest request) {
        // Retrieve the session object associated with the current request.
        HttpSession session = request.getSession();

        // Get the value of the "name" parameter from the URL.
        String paramName = request.getParameter("name"); // You use the parameter "name" in the URL to pass in a value.

        System.out.println("Name: " + paramName);

        // Set the attribute "attributeName" to the value of the "paramName" variable.
        // You use the attribute "attributeName" in the HTML file to retrieve the value of the "paramName" variable.
        session.setAttribute("attributeName", paramName);

        return "welcome.html";
    }

    // Method 2: Using @RequestParam and HttpSession injection
    @RequestMapping("/method2")
    public String welcome2(String name, HttpSession session) {
        session.setAttribute("attributeName", name);
        return "welcome.html";
    }


    // Method 3: Using @RequestParam to specify the parameter name when it differs from the method parameter name
    @RequestMapping("/method3")
    public String welcome3(@RequestParam("name") String myName, HttpSession session) {
        session.setAttribute("attributeName", myName);
        return "welcome.html";
    }

    // Method 4: Using ModelAndView as it provides more flexibility.
    @RequestMapping("/method4")
    public ModelAndView welcome4(@RequestParam("name") String myName) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("attributeName", myName);
        mv.setViewName("welcome");
        return mv;
    }
}

/*
A step-by-step explanation of what happens in Method 2 (`welcome2`):
        1. When a request is made to the URL mapped to `/method2`, the Spring DispatcherServlet receives the request.
        2. The DispatcherServlet looks for a suitable handler method to handle the request in our case it's' the `welcome2` method.
        3. The method signature includes two parameters: `String name` and `HttpSession session`.
        4. Spring Boot examines the method parameters and attempts to resolve them.
        5. Spring Boot resolves the `name` parameter by extracting the value from the "name" request parameter in the URL as the parameter name is not annotated with `@RequestParam`
        6. For the `HttpSession session` parameter, Spring Boot recognizes that it needs to inject an instance of the `HttpSession` object.
        7. Spring Boot injects the current `HttpSession` object into the `session` parameter.
        8. The method sets the value of the "attributeName" attribute in the session.
        9. Spring Boot processes the returned value. Since the return type is a `String` and not a complete URL, Spring Boot interprets it as the logical view name.
            For example, if the welcome2 method returns the String value "https://www.example.com/page", Spring Boot interprets it as a redirect URL.
            The framework will send a redirect response to the client, indicating that it should navigate to "https://www.example.com/page".
            In this case, Spring Boot doesn't perform any view resolution or rendering because the intent is to redirect the client to a different URL instead of
             returning a view for rendering.
        10. The `InternalResourceViewResolver` configured in the application maps the logical view name "welcome.html" to the actual view file.
        11. Spring Boot renders the view and sends it as the response to the client.


A step-by-step explanation of what happens in Method 4 (`welcome4`):
        1. When a request is made to the URL mapped to `/method4`, the Spring DispatcherServlet receives the request.
        2. The DispatcherServlet looks for a suitable handler method to handle the request in our case it's' the `welcome4` method.
        3. The method signature includes one parameter: `String myName`.
        4. Spring Boot resolves the `myName` parameter by extracting the value from the "name" request parameter in the URL.
        5. The method creates a new instance of the `ModelAndView` class. `ModelAndView` is a class provided by Spring that combines a model (data)
           and a view (template) for rendering the response.
        6. The value of the "myName" parameter is added to the model using `mv.addObject("attributeName", myName)`.
        7. The logical view name "welcome.html" is set using `mv.setViewName("welcome.html")`.
        8. Spring Boot processes the returned `ModelAndView` object. It recognizes that the returned object contains both model attributes and the view name.
        9. The `ModelAndView` object `mv` is returned from the method.
        10. Spring Boot uses an appropriate view resolver (such as `InternalResourceViewResolver`) to map the logical view name "welcome.html" to the actual view file.
        11. Spring Boot renders the view and sends it as the response to the client.
 */