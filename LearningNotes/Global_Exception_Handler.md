# Global Exception Handler

- In Java, exception handling is done by `try`, `catch` blocks but spring boot also allows us to provide customized global exception handling where we need not to add `try catch` block everwhere, we can create a separate class for handling exceptions and it also separates the exception handling code from businesss logic code.

- **Global exception handler** is a mechanism to catch all exceptions that are not caught by the application code. It is a good practice to have a global exception handler in your application to catch all unhandled exceptions and log them to a file or send an email to the development team.

- This handler can perform tasks such as logging the error details, returning custom error responses to clients, or taking appropriate recovery actions.

- When an exception occurs, the Spring Boot application will redirect it to the `GlobalExceptionHandler`. Depending on the specific type of exception, the `GlobalExceptionHandler` can generate an appropriate error message and return it to the client. This helps provide more meaningful error messages to the clients. It can also be used to log detailed information about exceptions.

## Creating a Global Exception Handler

- To create a global exception handler in a Spring Boot application, you can create a class annotated with `@ControllerAdvice` and define methods annotated with `@ExceptionHandler` to handle specific exceptions.

- The `@ControllerAdvice` tells Spring Boot that this class will handle exceptions across all controllers in the application.

- The `@ExceptionHandler` annotation is used to define a method that will handle a specific type of exception. You can define multiple `@ExceptionHandler` methods to handle different types of exceptions.

- Here's an example of a global exception handler in a Spring Boot application:
    ```java
    public class ResourceNotFoundException extends RuntimeException{
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    @ControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", ex.getMessage());
            body.put("status", HttpStatus.NOT_FOUND.value());

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<Object> handleGenericException(Exception ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", "An unexpected error occurred :(");
            body.put("details", ex.getMessage());
            body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    ```
    Here we have defined `GlobalExceptionHandler` class with two `@ExceptionHandler` methods. The first method handles `ResourceNotFoundException` and returns a custom error response with status code 404. The second method handles all other exceptions and returns a generic error response with status code 500.

-------------------