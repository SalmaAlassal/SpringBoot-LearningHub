# Sprint Boot Annotations

Annotations are special notes added to code. Spring Boot annotations provide instructions to the Spring Boot framework, but they don't change how your code works directly. They help Spring Boot configure your application and enable specific features, making development easier.

For example, let's say you use the `@RestController` annotation on a class. This annotation tells Spring Boot that this class should handle web requests and send back responses. Spring Boot reads this instruction and sets up everything behind the scenes to make it happen.

# List of Annotations

Here's a list of the annotations that I've used in my code examples:

## General Annotations

### `@SpringBootApplication`

- This annotation is used to mark the main class of your Spring Boot application. It tells Spring Boot that this class is the starting point of your application.

- This annotation is a combination of three annotations:
   - `@EnableAutoConfiguration`
   - `@ComponentScan`
   - `@SpringBootConfiguration`


- `@EnableAutoConfiguration` - Automatically configures your Spring Boot application based on the dependencies you have added.
   - `@EnableAutoConfiguration` attempts to guess and configure the beans which you may need, based on the classpath. By looking the package dependencies you have specified, SpringBoot will automatically create beans.
   - For instance, if Spring Boot detects `Hibernate` on the classpath, it automatically configures a `DataSource`, `EntityManagerFactory`, and `TransactionManager` for JPA persistence. Similarly, if Spring Security is present, it configures security-related beans like `AuthenticationManager` and `FilterChainProxy`.
- `@ComponentScan` - Scans the **package and its sub-packages** for components (e.g., controllers, services, repositories) and registers them with the Spring IoC container.
   - For example, if you have a controller class in the `com.example.controller` package, Spring Boot will find it and register it as a controller bean.
   - If you need to specify a different base package, you do so by passing the package name as an argument to the `@ComponentScan` annotation.
   ```java
   package com.example.e1;
   @SpringBootApplication
   @ComponentScan("com.example")
   public class MyApplication {
       public static void main(String[] args) {
           SpringApplication.run(MyApplication.class, args);
       }
   }
   ```
- `@SpringBootConfiguration` - Indicates that this class is a configuration class for the Spring Boot application.
   - This annotation is a specialized form of the `@Configuration` annotation. It tells Spring Boot that this class should be used to configure the application context.
   - The difference between `@Configuration` and `@SpringBootConfiguration` is that `@SpringBootConfiguration` allows the configuration to be found automatically by Spring Boot.

--------------------------------------------

## Core Spring Boot Annotations

### `@Controller`

This annotation is used to mark a class as a controller. It tells Spring Boot that this class should handle web requests and send back responses.

- It returns the name of the view to be rendered, not the actual data. If you want to return `JSON` or `XML`, you'd have to manually annotate your methods with `@ResponseBody`.

### `@RestController`

The `@RestController` annotation is a convenience annotation that combines `@Controller` and `@ResponseBody`. It tells Spring Boot that this class should handle web requests and send back responses.

**Key Points about `@RestController` :**
   - It combines the `@Controller` and `@ResponseBody` annotations, so every method in a `@RestController` automatically serializes the return value (e.g., JSON or XML) directly to the HTTP response body, rather than resolving it as a view.

### `@Repository`

This annotation is used to mark a class as a repository. It tells Spring Boot that this class should be used to access the database.

### `@Service`

This annotation is used to mark a class as a service. It tells Spring Boot that this class should be used to perform business logic.

--------------------------------------------

## Spring MVC & REST Annotations

### `@GetMapping("/path")`

This annotation is used to map a GET request to a specific path. It tells Spring Boot that this method should be called when a GET request is made to the specified path.

### `PostMapping("/path")`

This annotation is used to map a POST request to a specific path. It tells Spring Boot that this method should be called when a POST request is made to the specified path.

### `@PutMapping("/path")`

This annotation is used to map a PUT request to a specific path. It tells Spring Boot that this method should be called when a PUT request is made to the specified path.

### `@DeleteMapping("/path")`

This annotation is used to map a DELETE request to a specific path. It tells Spring Boot that this method should be called when a DELETE request is made to the specified path.


###  `@RequestMapping(method = RequestMethod.GET, value = "/path")`

This annotation is used to map a request to a specific path.

You can specify the HTTP method and the path in the annotation.

### `@ResponseBody`

This annotation is used to mark a method as a response body. It tells Spring Boot that this method should return the response body which will be sent back to the client.

### `@RequestBody`


###  `@PathVariable`

This annotation is used to extract a path variable from the request URL. E.g., `/users/{id}`

### `@RequestParam`

This annotation is used to extract a request parameter from the request URL. E.g., `?name=Salma`

--------------------------------------------

## Spring JPA and Hibernate Annotations


### `@Query`

Specifies a JPQL or a native SQL query for custom repository methods.

### `@Entity`

This annotation is used to mark a class as an entity. It tells Spring Boot that this class should be mapped to a database table.

### `@Table`

This annotation is used to mark a class as a table. It tells Spring Boot that this class should be mapped to a database table.

### `@Id`

This annotation is used to mark a field as the primary key of an entity. It tells Spring Boot that this field should be mapped to the primary key column in the database table.

### `@GeneratedValue`

This annotation is used to mark a field as a generated value. It tells Spring Boot that this field should be generated by the database when a new record is inserted into the table.

- `strategy = GenerationType.IDENTITY` - This strategy will help us to generate the primary key value by the database itself using the auto-increment column option. It relies on the database’s native support for generating unique values.

- `strategy = GenerationType.SEQUENCE` - This strategy uses a database sequence to generate primary key values. It requires the usage of database sequence objects, which varies depending on the database which is being used.
   - Not all databases support sequences, so it’s essential to check database compatibility.

- `strategy = GenerationType.AUTO` - This strategy will let the persistence provider(e.g, hibernate) choose the generation strategy. It selects the strategy based on the database-specific dialect. It is the default generation type.


### `@Column`

This annotation is used to mark a field as a column. It tells Spring Boot that this field should be mapped to a column in the database table.

### JPA Relationships

- Relationships may be **bidirectional** or **unidirectional**.
- A **bidirectional** relationship has both an **owning side and an inverse side**.
   - `@ManyToOne` is **always the owning side** of a bidirectional association.
   - `@OneToMany` is **always the inverse side** of a bidirectional association.
   - `@OneToOne` the **owning side** is the entity with the table containing the **foreign key.**
   - `@ManyToMany` both sides can be the owning side. Choose what makes sense for your use case.

- A **unidirectional** relationship only has an **owning side.**

- **Unidirectional associations** are associations where only one entity knows about the other entity through a reference(foreign key). The entity that knows about the other entity is called the **owning entity** (parent), and the entity that is known is called the **inverse entity**(child). Only the **owning entity** has a reference to the **inverse entity**.

- **Bidirectional associations** are associations where both entities know about each other. Each entity has a reference to the other entity.

- In JPA, the **“owning side”** of a relationship refers to the entity that takes charge of managing and updating the **join table**. On the flip side, the **“inverse side”** (denoted by the `mappedBy` attribute) mirrors the relationship but doesn’t interfere with the **join table** management.
   - Consider a relationship between two entities: `Author` and `Book`. If `Author` is the owning side, it implies that `Author` is accountable for updating the relationship between `Author` and `Book` in the database.


- **Owning Entity**:
   - **The owning side is typically the entity that most frequently manages changes in the relationship, thus updating the join table.**
   - It contorls the relationship between the two entities:
      - Any changes to the relationship, such as adding, removing, or updating the associated entities, are handled through the owning entity. For example, if you remove an entity from the owning entity’s collection, JPA will automatically remove the corresponding row(s) from the database based on the foreign key relationship.
   - It contains the foreign key column that references the primary key of the inverse entity.
   - You can use `@JoinColumn` annotation to customize the foreign key column name.
      - **Join Column**: The column in the owning entity’s table that stores the foreign key reference(s) to the other entity.

- **Inverse Entity**:
   - It is simply a reference to the owning entity and does not directly participate in persisting or managing the relationship in the database.

- Choosing the wrong entity as the owning side can lead to several issues and inefficiencies:
   - If we choose the `role` entity as the owning side, we might encounter the following issues:
      - **Unnecessary Database Operations:** If we add a new role to a user in our application and then save the user, the new role won’t be reflected in the database because User is not the owning side. This can lead to unnecessary database operations.
      - **Unexpected Behavior:** If we try to remove a role from a user and then save the user, we might expect the role to be removed from the user in the database. However, since User is not the owning side, the role won’t be removed in the database.


- You can have unidirectional associations in JPA by using:
   - `@OneToOne` - `1:1` relationship
      - One instance of the current Entity refers to One instance of the referred Entity.
   - `@OneToMany` - `1:N` relationship
      - One instance of the current Entity has Many instances (references) to the referred Entity.
   - `@ManyToOne` - `N:1` relationship
      - Many instances of the current Entity refer to One instance of the referred Entity.
   - `@ManyToMany` - `M:N` relationship
      - Many instances of the current Entity refer to Many instances of the referred Entity.

- By default, hibernate creates a foreign key column that references the primary key of the inverse entity to join the two entities. The foreign key column name is the name of the inverse entity followed by `_id`. If you want to specify a different column name, you can use the `@JoinColumn` annotation.

- You can have bidirectional associations in JPA by using `mappedBy` attribute.

- `mappedBy` attribute is used on the **non-owning side** of the relationship to indicate that the relationship is managed by the other side.

- In this example, the `mappedBy` attribute specifies that the `Department` entity is the inverse side of a one-to-one relationship with the `Person` entity, and that the foreign key is on the `Person` side of the relationship.
```java
@Entity
public class Department {
    @OneToOne(mappedBy = "department")
    private Person person;
    // ...
}
```

### Cascade Types

- Cascading is suitable for Parent-Child **(‘Has a’)** associations where Parent entity's state changes extend to its Child entities.

- When you have a relationship between two entities, you need to consider what happens to related data when you perform operations on one entity. This is where the concept of cascading comes in.

- **Cascading** means that when an operation is performed on one entity, the same operation is also performed on related entities. For example, if a parent entity is deleted, the child entity should also be deleted.

- **Without Cascade:**
   - If you delete a Department without any cascade settings, only the Department record will be deleted. The Employee records that were associated with this Department will remain in the database, but they'll now have a null or invalid reference where their Department used to be. This can lead to data integrity issues.
- **With Cascade:**
   - If you set up cascading on the relationship between Department and Employee, when you delete a Department, all associated Employee records will also be deleted automatically. This maintains data integrity but means you lose all Employee data for that Department.
   ```java
   @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
   private List<Employee> employees;
   ```

- **JPA Cascade Types**: `ALL`, `PERSIST`, `MERGE`, `REMOVE`, `REFRESH`, `DETACH`
- **Hibernate Cascade Types**: JPA Cascade Types + `REPLICATE`, `SAVE_UPDATE`, `LOCK`

- `CascadeType.ALL` - Propagates all operations from the parent entity to the child entity.
- `CascadeType.PERSIST` - Propagates the `persist` (create) operation from the parent entity to the child entity.
- `CascadeType.MERGE` - Propagates the `merge` (update) operation from the parent entity to the child entity.
- `CascadeType.REMOVE` - Propagates the `remove` (delete) operation from the parent entity to the child entity.

### `@JoinColumn`

- This annotation is used to mark a field as a join column. It's on the side of the entity that owns the foreign key column.

- If not specified, JPA will create a default join column.

- In this example, the `@JoinColumn` annotation specifies that the `Person` entity has a foreign key column called `department_id`, which refers to the primary key of the `Department` entity.

```java
@Entity
public class Person {
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    // ...
}
```

[@JoinColumn Configurations](https://codingnomads.com/spring-data-jpa-joincolumn-configuration)

### `@Temporal`

- The `@Temporal` annotation is used in JPA to convert a date and time field in the entity to a SQL type (`DATE`, `TIME`, or `TIMESTAMP`) when mapping to a database. This is crucial for managing how date and time values are persisted and retrieved in applications that use relational databases.

- Limited to `java.util.Date` and `java.util.Calendar`. Does not support `java.time` classes.

- **Types**:
   - `TemporalType.DATE` - Maps the field to a database column of type `DATE`. It's used to store date values. Example: `2021-12-31`.
   - `TemporalType.TIME` - Maps the field to a database column of type `TIME`. It's used to store time values. Example: `23:59:59`.
   - `TemporalType.TIMESTAMP` - Maps the field to a database column of type `TIMESTAMP`. It's used to store date and time values. Example: `2021-12-31 23:59:59`.

--------------------------------------------

## Lombok Annotations

### `@Data`

This annotation is used to mark a class as a data class. It tells Lombok to generate getters, setters, `toString()`, `equals()`, and `hashCode()` methods for the class.

```java
@Data
public class User {
    private Long id;
    private String name;
    private String email;
}
```

### `@Getter`

This annotation is used to mark a field as a getter. It tells Lombok to generate a getter method for the field.

```java
@Getter
private String name;
```

### `@Setter`

This annotation is used to mark a field as a setter. It tells Lombok to generate a setter method for the field.

```java
@Setter
private String name;
```

--------------------------------------------
