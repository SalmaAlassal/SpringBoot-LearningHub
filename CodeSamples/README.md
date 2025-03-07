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


## Example 6 : [Student Form Web App With Content Negotiation](StudentFormWebAppWithContentNegotiation)

This example is an extension of the previous one with the addition of content negotiation which is the process of determining the response format for a given request.

> Content negotiation is the process of determining the response format for a given request. It is used to serve different representations of a resource at the same URI, so that the user agent can specify which representation is best suited for the user. For example, a web browser may prefer to receive a resource in HTML format, while an API client may prefer JSON format.

> Postman was used to test the API endpoints.

## Example 7 : [RESTful Web Demo](RESTfulWebDemo)

This example builds upon the previous one, introducing the usage of the `@RestController` annotation to create a RESTful web service. It showcases how to utilize the `@RequestBody` annotation to extract data from the request body and convert it into a Java object, along with accepting only JSON or XML data as input. 

Furthermore, we demonstrate how to perform various HTTP requests, including GET, POST, PUT, and DELETE, using the popular tool Postman. 

## Example 8 : [RESTfulWebDemoWithJPA](RESTfulWebDemoWithJPA)

This example demonstrates how to use Spring Boot Data REST to create a RESTful web service, which automatically exposes your JPA repositories as RESTful endpoints. It simplifies the process of creating RESTful APIs by eliminating the need to write boilerplate code for basic CRUD (Create, Read, Update, Delete) operations on your data entities. You don't need to write any controller classes or methods to handle HTTP requests as the previous example.

## Example 9 : [Phone Number Validation](PhoneNumberValidation)

This example demonstrates how to utilize the `RestTemplate` class for making HTTP requests to the API and converting the response into a Java object. It leverages the [NumVerify API](https://numverify.com/) to validate phone numbers. The API is available for free and provides a JSON response that includes details about the phone number, such as the country, location, carrier, and line type.

## Example 10 : [CascadeTypeDemo](CascadeTypeDemo)

This example demonstrates the usage of the `CascadeType` attribute in JPA and the differnce between `CascadeType.REMOVE` and `OrphanRemoval`. Try to comment and uncomment the code to see the difference.

## Example 11 : [GlobalExceptionHandlerDemo](GlobalExceptionHandlerDemo)

This example demonstrates how to create a global exception handler. It showcases how to handle exceptions globally in a Spring Boot application using the `@ControllerAdvice` annotation and the `@ExceptionHandler` annotation. The global exception handler intercepts exceptions thrown by the application and provides a centralized mechanism to handle them, returning a custom error response to the client.

## Example 12 : [TestingDemo](TestingDemo)

This example demonstrates how to write unit tests for a Spring Boot application using JUnit 5 and Mockito. It showcases how to test controllers and repositories.

## Example 13 : [SpringBatchDemo](SpringBatchDemo)

This example demonstrates how to use Spring Batch. It showcases the creation of a simple batch job with 2 steps and listeners.

## Example 14 : [SpringBatchDemoWithCSV](SpringBatchDemoWithCSV)

This example demonstrates how to use Spring Batch to read data from a CSV file and write it to a database (H2 in-memory database).

## Example 15 : [TransactionsBatchProcessor](TransactionsBatchProcessor)

This example demonstrates how to use Spring Batch to read data(transactions data) from a CSV file and process it to successfull and failed transactions and write each to a different table in the database (H2 in-memory database).