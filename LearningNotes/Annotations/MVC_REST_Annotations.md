# MVC REST Annotations

- **Spring MVC (Model-View-Controller)** is a design for building web applications that follow the traditional Model-View-Controller pattern. We use Spring MVC for creating web applications where we need to handle both the presentation layer (views) and the application logic (controllers). 

- **Spring REST** is used to build RESTful web services. The server doesn’t return a view but instead returns data, often in JSON or XML format. It focuses on handling HTTP requests and responses in a RESTful way.

## `@Controller`

- It tells Spring Boot that this class should handle web requests and send back responses.

- It returns the name of the view to be rendered, not the actual data. If you want to return `JSON` or `XML`, you'd have to manually annotate your methods with `@ResponseBody`.

```java
@Controller
public class MyController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello"; // returns the name of the view to be rendered
    }
}
```
---------------------------------------------------------

## `@RestController`

- It combines the `@Controller` and `@ResponseBody` annotations. Every method in a `@RestController` automatically serializes the return value (e.g., JSON or XML) directly to the HTTP response body, rather than resolving it as a view.

```java
@RestController
public class MyRestController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello"; // returns the actual data
    }
}
```
---------------------------------------------------------

## `@RequestMapping`

- It is used to map a request to a specific path. You can use it at the class level or the method level.

- It's recommended to use it at the class level to define the base path for all the methods in the class.

- Parameters:
    - `value` - The path to map the request to.
    - `method` - The HTTP method (e.g., GET, POST, PUT, DELETE).
        - You can also use `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, `@PatchMapping` instead of specifying the method in the `@RequestMapping` annotation.
    - `params` - The parameters that the request must contain.
    - `consumes` - The media type that the method can consume (the content type of the request).
    - `produces` - The media type that the method can produce (the content type of the response).

```java
// localhost:8080/api/hello?name=Salma
@RestController
@RequestMapping("/api")
public class Test {
    @RequestMapping(value = "/hello", method = RequestMethod.GET, params = "name", produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello(@RequestParam String name) {
        // Return a JSON response
        return "{\"message\":\"Hello, " + name + "!\"}";
    }
}
```
---------------------------------------------------------

## `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`

- It is used to map a request to a specific path with a specific HTTP method.
    ```java
    @GetMapping("/path")
    public String method() {
        return "response";
    }
    ```
---------------------------------------------------------

## `@RequestBody`

## `@ModelAttribute`

## `@CrossOrigin`

## `@RequestParam`