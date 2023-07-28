# Sprint Boot Annotations

Annotations are special notes added to code. Spring Boot annotations provide instructions to the Spring Boot framework, but they don't change how your code works directly. They help Spring Boot configure your application and enable specific features, making development easier.

For example, let's say you use the `@RestController` annotation on a class. This annotation tells Spring Boot that this class should handle web requests and send back responses. Spring Boot reads this instruction and sets up everything behind the scenes to make it happen.

## List of Annotations

Here's a list of the annotations that I've used in my code examples:

### `@SpringBootApplication`

This annotation is used to mark the main class of your Spring Boot application. It tells Spring Boot that this class is the starting point of your application.


### `@Controller`

This annotation is used to mark a class as a controller. It tells Spring Boot that this class should handle web requests and send back responses.


### `GetMapping("/path")`

This annotation is used to map a GET request to a specific path. It tells Spring Boot that this method should be called when a GET request is made to the specified path.

### `@ResponseBody`

This annotation is used to mark a method as a response body. It tells Spring Boot that this method should return the response body which will be sent back to the client.

###  `@RequestMapping(method = RequestMethod.GET, value = "/path")` 

This annotation is used to map a request to a specific path. It tells Spring Boot that this method should be called when a request is made to the specified path.

### `RequestMapping(method = RequestMethod.POST, value = "/path")`

This annotation is used to map a POST request to a specific path. It tells Spring Boot that this method should be called when a POST request is made to the specified path.

###  `@PathVariable` 

This annotation is used to mark a method parameter as a path variable. It tells Spring Boot that this parameter should be populated with the value of the path variable in the request URL. 
