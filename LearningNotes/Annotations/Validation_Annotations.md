# Validation Annotations

- **Validation** is the process of ensuring that the data entered by the user meets certain criteria. It helps to prevent invalid data from being stored in the database.

- **Spring Boot Validation** is a powerful mechanism to validate the data entered by the user. It provides a set of annotations that can be used to validate the data before it is processed.

- Dependency for validation in `pom.xml`:
    ```xml
    <dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-validation</artifactId>
	</dependency>
    ```

## `@NotNull`

- The field is required and must not be null.

```java
@NotNull
private String name;
```

## `@NotBlank`

- The field must not be null and must contain at least one non-whitespace character.

```java
@NotBlank
private String name;
```

## `@NotEmpty`

- Guarantees that collections or arrays are not empty.

```java
@NotEmpty
private List<String> names;
```

## `@Min(value)`

- The field must be a number greater than or equal to the specified value.

```java
@Min(18)
private int age;
```

## `@Max(value)`

- The field must be a number less than or equal to the specified value.

```java
@Max(100)
private int age;
```

## `@Size(min, max)`

- Validates if a string or collection size is within a specific range.

```java
@Size(min = 2, max = 50)
private String name;
@Size(min = 1, max = 10)
private List<String> names;
```

## `@Email`

- Ensures a field contains a valid email address format.

```java
@Email
private String email;
```

## `@Digits(integer, fraction)`

- Validates that a numeric field has a specified number of integer and fraction digits.

```java
@Digits(integer = 3, fraction = 2)
private BigDecimal amount;
```

## `@Past` and `@Future`

- Ensures that a date field is in the past or future.

```java
@Past
private LocalDate startDate;
@Future
private LocalDate endDate;
```

## `@AssertTrue` and `@AssertFalse`

- Validates that a boolean field is true or false.

```java
@AssertTrue
private boolean active;
@AssertFalse
private boolean deleted;
```

## `@Valid`

- Primarily used to trigger validation on an object, especially on nested objects.

- When placed before a method parameter, Spring’s validation mechanism inspects the object for any constraints (like `@NotNull`, `@Size`, etc.) and ensures that they are satisfied for that parameter before the method is invoked. This means that the incoming data for that parameter will be validated against the specified validation rules

```java
public class UserProfile {  
    @Valid  
    private Address address;  
}

public class Address {  
    @NotBlank  
    private String street;  
    @NotBlank  
    private String city;  
    @NotBlank  
    private String zipCode;  
}
```
When the `UserProfile` object is validated, the `@Valid` annotation will trigger the validation of the `Address` object as well and ensure that the `street`, `city`, and `zipCode` fields are not blank.

### `@Valid` in Controller

- Validation often takes place in the controller, where user input is received.
- The `BindingResult` parameter is used to capture the validation errors that occur during the validation process.

```java
@Controller
public class UserController {
    @PostMapping("/register")
    public String registerUser(@Valid UserRegistrationForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "registrationForm"; // Return back to the form with error messages
        }
        // If no errors, proceed with user registration
        // ...
        return "redirect:/login";
    }
}
```
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Handle validation errors
            return ResponseEntity.badRequest().body("Validation errors found.");
        }
        // Process user and create a new user
        // ...
        return ResponseEntity.ok("User created successfully.");
    }
}
```


## `@Validated`

- Specifies validation groups to be applied at the class or method level.

-  Unlike the standard `@Valid` annotation, which validates the entire bean object, `@Validated` allows you to specify which validation groups to apply during the validation process.

```java
public class User {  
  
    @NotBlank(groups = Creation.class)  
    private String username;  
  
    @Size(min = 8, groups = Update.class)  
    private String password;  
}  
  
public interface Creation {}  
public interface Update {}
```

when creating a user, only the `username` constraint is validated. When updating, only the `password` constraint is checked.

- **Example:**
    - **Define Validation Groups:**
        ```java
        public interface BasicInfo {}
        public interface AdvancedInfo {}
        ```
        ```java
        public class User {

            @NotNull(groups = BasicInfo.class)
            @NotNull(groups = AdvancedInfo.class)
            private String username;

            @NotNull(groups = AdvancedInfo.class)
            private String email;
            // Other fields, getters, setters
        }
        ```
    - **Apply Validation Groups in Controller:**
        ```java
        import org.springframework.validation.annotation.Validated;
        @Controller
        @Validated
        public class UserController {
            @PostMapping("/registerBasicInfo")
            public String registerBasicInfo(@Validated(BasicInfo.class) @ModelAttribute UserRegistrationForm form, BindingResult result) {
                if (result.hasErrors()) {
                    return "basicInfoForm";
                }
                // Process basic info registration
                return "redirect:/success";
            }
            @PostMapping("/registerAdvancedInfo")
            public String registerAdvancedInfo(@Validated(AdvancedInfo.class) @ModelAttribute UserRegistrationForm form, BindingResult result) {
                if (result.hasErrors()) {
                    return "advancedInfoForm";
                }
                // Process advanced info registration
                return "redirect:/success";
            }
        }
        ```

## `BindingResult`

- The `BindingResult` is used to capture the validation errors that occur during the validation process.

- Example of getting the error messages from the `BindingResult` object:
    ```java
    @RestController
    @RequestMapping("/api")
    public class ValidationController {

        @Autowired
        private Validator validator;

        @PostMapping("/validate")
        public ResponseEntity<String> validateData(@RequestBody @Valid Data data, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                for (ObjectError error : bindingResult.getAllErrors()) {
                    String errorMessage = error.getDefaultMessage(); // Get the error message
                }
                return ResponseEntity.badRequest().body("Validation failed");
            }
            return ResponseEntity.ok("Data validated and processed successfully");
        }
    }
    ```