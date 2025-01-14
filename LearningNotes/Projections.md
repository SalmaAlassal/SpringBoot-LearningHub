# Spring Data JPA Projections

- **Projections** provide a way to retrieve only a subset of fields from an entity. This is particularly useful in optimizing performance, reducing memory usage, and securing data access by fetching only the necessary fields. Instead of loading an entire entity with all its attributes, projections allow you to specify exactly which fields you want.

- **Types of Projections**:
    - **Interface-based Projections**: You can define a projection interface that declares getter methods for the fields you want to include in the result.
        - **Nested Projections**: You can include nested projections to select a subset of fields from related entities. This is useful when we need to retrieve fields from related entities but want to avoid fetching the entire entities.
        - **Projections can be classified as:**
            - **Closed Projections**: The name of getter method should match with the name of the entity field.
                - If you use a closed projection, Spring Data can optimize the query execution, because we know about all the attributes that are needed to back the projection proxy. 
            - **Open Projections**: It allow you to include custom logic or derived fields by using SpEL (Spring Expression Language) (using `@Value`). You can return calculated fields, name methods differently, etc.
                - If you use an open projection, Spring Data cannot optimize the query execution, because we don't know about all the attributes that are needed to back the projection proxy.
    - **Class-based Projections (DTOs)**: You can define a projection class that includes the fields you want to include in the result.
        - It require a default constructor and matching property names with the entity. This can be cumbersome if you have a lot of fields or different naming conventions.
        - We cannot use nested projections as we can with interface-based approaches.
        - They are not compatible with dynamic projections. You cannot use a generic repository method to specify the projection type at runtime, as you can with interface-based projections.
        - You can also use `record`.
    - **Dynamic Projections**: You can retrieve data in different forms without defining multiple repository methods.


------------------------------------------------

## Interface-based Projections

### How Does Interface Projection Work Internally?

- Behind the scenes, when you use an interface-based projection in Spring Data JPA, Spring creates a **dynamic proxy** to handle the projection.

- **Database Query Execution:** 
    - When you invoke a method from a repository that returns a projection, Spring Data JPA generates a SQL query based on the projection interface. This query only selects the fields specified in the projection.
- **Proxy Instantiation:**
    - For each record, Spring creates a proxy object that implements the projection interface at runtime.
    - The proxy is populated with the data from the database.
- **Getter Invocation:**
    - When you call a getter on the projection, the proxy retrieves and returns the corresponding field.

```java
@Getter @Setter
@Entity
@Table(name = "customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String lastName;
    private String email;   

    private Address address;     
}
```
The projection interface defines getter methods for the fields you want to include in the result.

```java
public interface CustomerProjection {
    String getFirstName();
    String getLastName();
}
```

The repository method returns a list of `CustomerProjection` objects, which contain only the `firstName` and `lastName` fields.
```java
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<CustomerProjection> findAllProjectedBy();
}
```

## Nested Projections

```java
public interface CustomerAddressProjection {
    interface CustomerProjection {
        String getFirstName();
        String getLastName();
    }
    interface AddressProjection {
        String getStreet();
        String getCity();
        String getZipCode();
    }
    CustomerProjection getCustomer();
    AddressProjection getAddress();
}
```
or:

```java
public interface CustomerAddressProjection {
    String getFirstName();
    String getLastName();
    AddressProjection getAddress();

    interface AddressProjection {
        String getStreet();
        String getCity();
        String getZipCode();
    }
}
```

The repository method returns a list of `CustomerAddressProjection` objects, which contain the `firstName`, `lastName`, `street`, `city`, and `zipCode` fields.
```java
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<CustomerAddressProjection> findAllProjectedBy();
}
```



## Closed Projections

```java
public interface CustomerProjection {
    String getFirstName();
    String getLastName();
}
```

## Open Projections

```java
public interface CustomerProjection {
    String getFirstName();
    String getLastName();
    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();
    @Value("#{'Hello, ' + target.firstName + ' ' + target.lastName}")
    String getGreeting();
}
```

## Class-based Projections (DTOs)

### How Does DTO Class Projection Work Internally?

- When a repository method returns a DTO class projection, Spring Data JPA checks the DTO class for a suitable constructor that matches the repository method's result.

- When the query is executed, Spring Data JPA creates instances of the DTO class using the constructor that matches the query result.


```java
public class StudentWithStatusDTO {
    private String name;
    private String major;
    private boolean isAdult;

    public StudentWithStatusDTO(String name, String major, Integer age) {
        this.name = name;
        this.major = major;
        this.isAdult = age >= 18;
    }

    // Getters
}
```

the repository method returns a list of `StudentWithStatusDTO` objects, which contain the `name`, `major`, and `isAdult` fields.
```java
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT new com.example.StudentWithStatusDTO(s.name, s.major, s.age) FROM Student s WHERE s.age > ?1")
    List<StudentWithStatusDTO> findStudentWithStatusByAge(Integer minAge);
}
```

## Record-based Projections

```java
public record CustomerRecord(String firstName, String lastName) {}
```

## Dynamic Projections

```java
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    <T> T findByFirstName(String firstName, Class<T> type);
}
```

The `findByFirstName` method retrieves a `Customer` entity by the `firstName` field and returns a projection of the specified type. The type parameter `T` is the projection interface or class that defines the fields you want to include in the result.

```java
public interface CustomerView {
    String getFirstName();
    String getLastName();
}


public interface AdminView {
    String getFirstName();
    String getLastName();
    String getEmail();
}
```

The `findByFirstName` method can return a `CustomerView` or `AdminView` projection based on the specified type.

```java
CustomerView customerView = customerRepository.findByFirstName("Alice", CustomerView.class);
AdminView adminView = customerRepository.findByFirstName("Alice", AdminView.class);
```

------------------------------------------------

## When to Use Each Type of Projection

- **Closed Projections:** Best used when you need to optimize performance by fetching only specific fields from the database, ensuring minimal data transfer and processing.

- **Open Projections:** Ideal for cases where you need to include derived fields or custom logic in the projection. However, be cautious of potential performance impacts due to loading the entire entity.

- **Class-based Projections (DTO Projections):** Perfect for complex data transformations or when you need to encapsulate the result set into a more meaningful object. This is especially useful in service-oriented architectures.
    -  you should use class-based projections only when you need to create complex or custom projections that cannot be achieved with interface-based projections. Otherwise, you may be better off using interface-based projections, which are simpler and more efficient.

- **Dynamic Projections:** Useful when the application requires flexibility to switch between data views based on runtime conditions or user preferences.

------------------------------------------------