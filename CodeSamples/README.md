# Spring Boot Code Samples

Here, you will find a collection of code snippets and examples that I've used during my learning journey. 

Each example is self-contained and can be run independently. 

## List of Examples

### Example 1 : [HelloWebApp](HelloWebApp)

This example covers two main topics: understanding controllers and handling GET requests, as well as learning about the `application.properties` file for configuration.

### Example 2 : [WelcomeWebApp](WelcomeWebApp)

This example covers various approaches for handling user input in a web application using the Thymeleaf templating engine. The application allows users to input data through a URL parameter and generates a customized response based on that input.

We examine different techniques, including the usage of `ModelAndView`, `@RequestParam` annotation, and `HttpSession` injection, to effectively capture and process user input in the application.

### Example 3 : [StudentWebApp](StudentWebApp)

This example focuses on creating a student class and utilizing it as a parameter in a web application. The program allows users to input student information and displays the entered data on a web page.

### Example 4 : [StudentFormWebApp](StudentFormWebApp)

This example focuses on allowing users to get and add new students through a form and saves the student data to the database. The H2 database is configured to store the student data. Additionally, it has a script that automatically populates the database with sample data.

## Example 5 : [StudentFormWebAppWithCRUD](StudentFormWebAppWithCRUD)
 
This example is an extension of the previous one, building upon the usage of `CrudRepository` to perform CRUD operations on the database. It introduces custom query methods that follow a specific naming convention, enabling us to define personalized search criteria without the need for writing explicit SQL queries. In addition, this example employs JPQL Query for more advanced querying capabilities.

Using these custom query methods, we retrieve data from the database and present it on a web page. The application demonstrates how to display information of a particular student based on a specific attribute, such as their used technology.