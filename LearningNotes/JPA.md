# JPA & Spring Data JPA

## Data Persistence

- Data persistence refers to **the capability of an application to save data so that it can be retrieved and used later,** even after the application has closed or the system has been restarted. 

- A simple way to achieve data persistence for a Java application is through **JDBC.** This does the job of achieving connectivity between a Java application and its storage system, persist data and retrieve it exactly as present in the schema of the database table. Up until JDBC, our focus was solely on achieving the connection between a Java application and its database to automate CRUD operations. But building an enterprise application with JDBC alone for data persistence is taxing and inefficient. This is because **JDBC requires additional coding to map the application’s object-oriented data representation to a relational database model and its schema.** In other words, it requires us to manually translate the transient java objects into relational database schema and back again. This, for an enterprise application, becomes extensively taxing, especially if it implements MVC pattern. Hence we can safely conclude that JDBC lacks in supporting object-to-relational database mapping termed as Object Relational Mapping (ORM).

## Transient vs Persistent Object

- **Transient Object:** An object is said to be transient if it is just created and is not saved in the database.
- **Persistent Object:** An object is said to be persistent if it is saved in the database.

- For example, when you get an entity from a repository, that entity is persistent. When you create a new entity, it is transient until persisted.

## JDBC

- **JDBC (Java Database Connectivity)** provides a set of Java APIs to access the relational databases from the Java program. Java APIs enable programs to execute SQL statements and interact with any SQL database.

## ORM

- ORM stands for **Object-Relational Mapping**. 

- ORM is a programming technique that maps objects to relational databases. ORM allows developers to work with objects in their code and automatically handles the mapping to and from the database. 

![ORM](./imgs/orm.png)

## JPA

- JPA stands for **Java Persistence API**. It is a Java specification for accessing, persisting, and managing data between Java objects / classes and a relational database.

- **JPA manages ORM in a Java application.** JPA defines a set of classes and interfaces without defining its implementation. Hence, an additional ORM tool is required to implement these interfaces. So, ORM tools like `Hibernate`, `TopLink`, `OpenJPA` `EclipseLink` and `iBatis` implements JPA specifications for data persistence.

- **JPA is just a specification,** JPA is not a framework. It defines a concept that can be implemented by any framework. It requires an implementation.

- As an object-oriented query language, it uses **JPQL (Java Persistent Query Language)** to execute database operations. The role of JPA is to transform JPQL into SQL.

- A point to be noted here is that **JPA still uses JDBC under the hood** which helps in dealing with relational database models.

- `javax.persistence` package contains the JPA classes and interfaces. In 2019, JPA renamed to **Jakarta Persistence** `jakarta.persistence`.

![JPA](./imgs/jpa.png)

> ORM vendors like Hibernate, TopLink..


## JPA vs Hibernate

- **JPA** is a java specification that is used to access, manage, and persist data between Java object and relational database. **It is a standard approach for ORM.**

- **JPA** is a set of rules and guidelines that must be followed. **Hibernate** is an ORM tool that implements these rules.

## Hibernate

- Hibernate is a framework which provides some abstraction layer, meaning that the programmer does not have to worry about the implementations, Hibernate does the implementations for you internally like Establishing a connection with the database, writing query to perform CRUD operations etc.

- The main feature of Hibernate is to map the Java classes to database tables.

- As an object-oriented query language, it uses **Hibernate Query Language (HQL)** to execute database operations.

- Hibernate is described in `org.hibernate package`.
    > It's recomended to use the standard JPA annotations from `javax.persistence` package. This way, you could theoretically run your code on other JPA implementations. Only when you need Hibernate-specific functionality should you use the Hibernate annotations.

## Spring Data JPA

- Spring Data JPA **adds a layer on the top of JPA.**

- Spring Data JPA abstracts JPA, which means that Spring Data JPA's Repository implementation uses JPA.

- Spring Data JPA always requires a JPA implementation such as Hibernate, EclipseLink, etc.

- It likes a jpa but it add some extra functionality, Without jpa we can not implement the spring data jpa.

- If you want to use JPA, you need to inject `EntityManager` and use it. But if you want to use Spring Data JPA, you need to inject `Repository` and use it. 

![Spring Data JPA](./imgs/spring_data_jpa.png)

- **Features:**
    - **Repository Pattern**: Spring Data JPA provides a repository abstraction that allows you to work with JPA entities without writing boilerplate code.
    - **Query Methods**: You can define queries using method names in the repository interface. Spring Data JPA will automatically generate the required SQL queries based on the method names.
    - **Pagination and Sorting**: Spring Data JPA provides built-in support for pagination and sorting of query results.

- **Spring Data Repositories**:
    - `CrudRepository`: It offers standard create, read, update, and delete It contains method like `findOne()`, `findAll()`, `save()`, `delete()`.
    - `PagingAndSortingRepository`: It extends `CrudRepository` and adds additional methods to retrieve entities using pagination and sorting.
    - `JpaRepository`: It extends both `CrudRepository` and `PagingAndSortingRepository` and provides additional JPA-specific methods like `flush()`.

### JPA Example vs Spring Data JPA Example

- **JPA Example**:
    ```java
    @Repository
    public class EmployeeDAO {
        @PersistenceContext
        private EntityManager entityManager;
        public void saveEmployee(Employee employee) {
            entityManager.persist(employee);
        }

        public List<Employee> getAllEmployees() {
            Query query = entityManager.createQuery("SELECT e FROM Employee e");
            return query.getResultList();
        }
    }
    ```

- **Spring Data JPA Example**:
    ```java
    public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    }
    ```
    ```java
    @Service
    public class EmployeeService {
        @Autowired
        private EmployeeRepository employeeRepository;
        public void saveEmployee(Employee employee) {
            employeeRepository.save(employee);
        }

        public List<Employee> getAllEmployees() {
            return employeeRepository.findAll();
        }
    }
    ```

--------------------------------------------

## JPA Architecture

![JPA Architecture](./imgs/jpa_architecture.webp)

The main components of JPA include:

- **Entity Manager Factory**: 
    - It is a factory class of `EntityManager` instances. 
    - It is used to create and manage multiple `EntityManager` instances. 
    - It is thread-safe and expensive to create, so **it should be created once per application** and reused.
    - It's created using the `Persistence` class.

- **Entity Manager**: 
    - It is created using the `EntityManagerFactory` instance.
    - It manages the lifecycle of entity instances, performs CRUD operations, and executes queries. 
    - It manages the lifecycle of entities, such as persisting, finding, merging, and removing them.    
    - It is not thread-safe and should be used per transaction or per request.

    ```java
    import javax.persistence.EntityManager;
    import javax.persistence.EntityManagerFactory;
    import javax.persistence.Persistence;

    public class Main {
        public static void main(String[] args) {
            
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyPersistenceUnit");

            EntityManager entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();

            // Perform CRUD operations using entityManager
            Manager manager = new Manager();
            manager.setName("John Doe");
            entityManager.persist(manager);

            Employee employee = new Employee();
            employee.setName("Alice");
            entityManager.persist(employee);

            // Commit the transactions made (persist, update, delete)
            entityManager.getTransaction().commit();

            entityManager.close();
            entityManagerFactory.close();
        }
    }
    ```

- **Entity**: 
    - It is a class that represents a table in the database. 
    - The Entity class maps to the required table schema present in the linked database. 
    - It is annotated with `@Entity` annotation.

- **Persistence Unit**: 
    - It is a logical grouping of related entity classes and their configurations.
    - It defines all entity classes managed by an `EntityManager`. 
    - Specifies database connection properties, transaction management type, and any additional settings.
    - It is defined in the `persistence.xml` file.
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <persistence version="2.1"
        xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">
        <persistence-unit name="TestPersistence" transaction-type="RESOURCE_LOCAL">
            <class><!-- Entity Manager Class Name --></class>
            <properties>
                <property name="javax.persistence.jdbc.url" value="Database Url" />
                <property name="javax.persistence.jdbc.user" value="Database Username" />
                <property name="javax.persistence.jdbc.password" value="Database Password" />
                <property name="javax.persistence.jdbc.driver" value="Database Driver Name" />
            </properties>
        </persistence-unit>
    </persistence>
    ```

    **Example**:
    ```xml
    <persistence-unit name="myPersistenceUnit">
        <class>com.example.demo.entity.Manager</class>
        <class>com.example.demo.entity.Employee</class>
        <properties>
            <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/mydb"/>
            <property name="javax.persistence.jdbc.user" value="root"/>
            <property name="javax.persistence.jdbc.password" value="root"/>
            <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>
        </properties>    
    </persistence-unit>
    ```



- **Entity Transaction**:
    - The `EntityTransaction` interface manages transaction boundaries. 
    - It allows you to begin, commit, and rollback transactions to ensure data consistency and integrity.
    - A transaction is a set of operations that either fail or succeed as a unit. 
    - A database transaction consists of a set of SQL DML (Data Manipulation Language) operations that are committed or rolled back as a single unit. 
    - It lives for a short period of time. It dies after the transaction is either committed or rolled back.

    ```java
    entityManager.getTransaction().begin();
    // Perform multiple operations
    entityManager.getTransaction().commit();
    ```

- **JPQL**: 
    - It is a query language that is used to execute queries on entities. It is similar to SQL but operates on entities rather than tables.


--------------------------------------------

## JPA Relationships

- Relationships may be **bidirectional** or **unidirectional**.
    - **Unidirectional associations** are associations where only one entity knows about the other entity through a reference(foreign key). The entity that knows about the other entity is called the **owning entity** (parent), and the entity that is known is called the **inverse entity**(child). Only the **owning entity** has a reference to the **inverse entity**.
        ```java
        @Entity
        public class Department {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;
            private String name;
        }

        @Entity
        public class Person {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;
            private String name;
            @ManyToOne
            private Department department;
        }
        ```

    - **Bidirectional associations** are associations where both entities know about each other. Each entity has a reference to the other entity.
        - You can have bidirectional associations in JPA by using `mappedBy` attribute.
        - `mappedBy` attribute is used on the **non-owning side** of the relationship to indicate that the relationship is managed by the other side.
        - In this example, the `mappedBy` attribute specifies that the `Department` entity is the inverse side of a OneToMany relationship with the `Person` entity, and that the foreign key is on the `Person` side of the relationship.

        ```java
        @Entity
        public class Department {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;
            private String name;
            @OneToMany(mappedBy = "department")
            private List<Person> persons;
        }

        @Entity
        public class Person {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;
            private String name;
            @ManyToOne
            private Department department;
        }
        ```


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
   - If we choose the `role` entity as the owning side instead of `user`, we might encounter the following issues:
      - **Unnecessary Database Operations:** If we add a new role to a user in our application and then save the user, the new role won’t be reflected in the database because User is not the owning side. This can lead to unnecessary database operations.
      - **Unexpected Behavior:** If we try to remove a role from a user and then save the user, we might expect the role to be removed from the user in the database. However, since User is not the owning side, the role won’t be removed in the database.


- **Association Mappings**:
   - `@OneToOne` - `1:1` relationship
      - One instance of the current Entity refers to One instance of the referred Entity.
   - `@OneToMany` - `1:N` relationship
      - One instance of the current Entity has Many instances (references) to the referred Entity.
   - `@ManyToOne` - `N:1` relationship
      - Many instances of the current Entity refer to One instance of the referred Entity.
   - `@ManyToMany` - `M:N` relationship
      - Many instances of the current Entity refer to Many instances of the referred Entity.


- A **bidirectional** relationship has both an **owning side and an inverse side**.
    - `@ManyToOne` is **always the owning side** of a bidirectional association.
    - `@OneToMany` is **always the inverse side** of a bidirectional association.
    - `@OneToOne` the **owning side** is the entity with the table containing the **foreign key.**
    - `@ManyToMany` both sides can be the owning side. Choose what makes sense for your use case.

- A **unidirectional** relationship only has an **owning side.**
   
- By default, hibernate creates a foreign key column that references the primary key of the inverse entity to join the two entities. The foreign key column name is the name of the inverse entity followed by `_id`. If you want to specify a different column name, you can use the `@JoinColumn` annotation.

### `@ManyToMany` Relationship

- When you have a many-to-many relationship in JPA, you generally need a **join table** to represent the relationship in the database. JPA can automatically create a join table for you if you don’t specify one explicitly. This approach is straightforward and works well for simple many-to-many relationships where you don’t need additional attributes in the join table.

- Example of JPA Creating a Join Table Automatically:
    ```java
    @Entity
    public class Student {
        @ManyToMany
        private Set<Course> courses;
    }

    @Entity
    public class Course {
        @ManyToMany(mappedBy = "courses")
        private Set<Student> students;
    }
    ```
    In this case, JPA will create a join table with default naming conventions, typically based on the entity class names and their primary key fields.


### Explicitly Define a Join Table `@JoinTable`

- If you need control over the join table structure, want to specify custom join column names, or require additional attributes in the join table, you should explicitly define it using the `@JoinTable` annotation.

- Explicitly defining a join table is useful when:
    - You need a specific table name or column names.
    - You want to add attributes to the join table.
    - You require unique constraints or other configurations.

- Example of Explicitly Defining a Join Table:
    ```java
    @Entity
    public class Student {
        @ManyToMany
        @JoinTable(
            name = "student_course", // Custom table name
            joinColumns = @JoinColumn(name = "student_id"), // Custom join column
            inverseJoinColumns = @JoinColumn(name = "course_id") // Custom inverse join column
        )
        private Set<Course> courses;
    }

    @Entity
    public class Course {
        @ManyToMany(mappedBy = "courses")
        private Set<Student> students;
    }
    ```

## Jackson Bidirectional Relationships

- **Jackson** is a popular Java library for serializing and deserializing Java objects to and from JSON. When serializing entities with bidirectional relationships, you may encounter issues with infinite recursion or stack overflow errors.

- **Infinite Recursion**: When serializing entities with bidirectional relationships, Jackson will keep serializing the same entities over and over again, leading to an infinite loop.
    - For example, when serializing a `Department` entity that has a list of `Employee` entities, Jackson will serialize the `Department` entity, then the `Employee` entities, which will in turn serialize the `Department` entity again, and so on.
        ```json
        {
            "id": 1,
            "name": "HR",
            "employees": [
                {
                    "id": 1,
                    "name": "Alice",
                    "department": {
                        "id": 1,
                        "name": "HR",
                        "employees": [
                            {
                                "id": 1,
                                "name": "Alice",
                                "department": {
                                    "id": 1,
                                    "name": "HR",
                                    "employees": [
                                        ...
                                    ]
                                }
                            }
                        ]
                    }
                }
            ]
        }
        ```

- **Solution**: To prevent infinite recursion, you can use Jackson’s `@JsonManagedReference` and `@JsonBackReference` annotations to define the owning and inverse sides of the relationship.

    - **`@JsonManagedReference`**: Used on the owning side of the relationship to indicate that this side is responsible for managing the relationship.
    - **`@JsonBackReference`**: Used on the inverse side of the relationship to indicate that this side should be ignored during serialization.
    ```java
    @Entity
    public class Student {
        @ManyToMany
        @JsonManagedReference
        private Set<Course> courses;
    }

    @Entity
    public class Course {
        @ManyToMany(mappedBy = "courses")
        @JsonBackReference
        private Set<Student> students;
    }
    ```
    
> Serialization means converting an object into a stream of bytes to store the object or transmit it to memory, a database, or a file. Its main purpose is to save the state of an object to be able to recreate it when needed.

> A byte stream is just a stream of binary data. Because only binary data can be stored or transported.

> Deserialization is the exact opposite process of serialization where the byte data type stream is converted back to an object in the memory. 

--------------------------------------------

## Different Query Types in Spring Data Repositories

You can define queries in Spring Data JPA repositories using the following methods:

- **JPQL Queries**: It allows you to write queries using entity classes and fields. 
    ```java
    @Query("SELECT s FROM Student s JOIN s.courses c WHERE c.id = :courseId")
    List<Student> findStudentsByCourseId(@Param("courseId") Long courseId);
    ```
- **Native Queries**: It permits the execution of SQL queries directly.
    ```java
    @Query(value = "SELECT * FROM student_course WHERE course_id = :courseId", nativeQuery = true)
    List<Map<String, Object>> findStudentCourseMappings(@Param("courseId") Long courseId);
    ```
- **Derived Query Methods**: It allows you to create queries based on method names. 
    ```java
    List<Student> findByCoursesId(Long courseId);
    ```

--------------------------------------------

## Cascading

- **Cascading** means that when an operation is performed on one entity, the same operation is also performed on related entities. For example, if a parent entity is deleted, the child entity should also be deleted.

- Cascading is suitable for Parent-Child **(‘Has a’)** associations where Parent entity's state changes extend to its Child entities.

- For example the Person–Address relationship. Without the Person, the Address entity doesn’t have any meaning of its own. When we delete the Person entity, our Address entity should also get deleted.

- **Without Cascade:**
   - If you delete a Department without any cascade settings, only the Department record will be deleted. The Employee records that were associated with this Department will remain in the database, but they'll now have a null or invalid reference where their Department used to be. This can lead to data integrity issues.
- **With Cascade:**
   - If you set up cascading on the relationship between Department and Employee, when you delete a Department, all associated Employee records will also be deleted automatically. This maintains data integrity but means you lose all Employee data for that Department.
   ```java
   @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
   private List<Employee> employees;
   ```

### Cascade Types

- **JPA Cascade Types**: `ALL`, `PERSIST`, `REMOVE`, `MERGE`, `REFRESH`, `DETACH`
    - `CascadeType.ALL` - Propagates all operations from the parent entity to the child entity.
    - `CascadeType.PERSIST` - Propagates the `persist` (create) operation from the parent entity to the child entity.
    - `CascadeType.REMOVE` - Propagates the `remove` (delete) operation from the parent entity to the child entity.
    - `CascadeType.MERGE` - Propagates the `merge` (update) operation from the parent entity to the child entity.
- **Hibernate Cascade Types**: JPA Cascade Types + `REPLICATE`, `SAVE_UPDATE`, `LOCK`

#### `CascadeType.ALL`

- This cascade type specifies that all state transitions (create, update, delete, and refresh) should be cascaded from the parent entity to the child entities.

- E.g. If you have a customer entity with a one-to-many relationship to Order entity then all operations on the Customer entity should be cascaded to the Order entity. This means that when a Customer entity is saved, updated, or deleted, all of its associated Order entities will also be saved, updated, or deleted accordingly.

```java
@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
private Set<Order> orders;
```

#### `CascadeType.PERSIST`

- This cascade type specifies that the persist operation should be cascaded to the child entities. This means that whenever a persist operation is executed on the parent entity, the persist operation is also cascaded to the child entities. However, updates or deletions made to the parent entity will not be cascaded to the child entities.

- E.g. If you have a customer entity with a one-to-many relationship to Order entity then the persist operation on the Customer entity will be cascaded to the Order entity automatically. However, updates or deletions made to the Customer entity will not be cascaded to the associated Order entities.

```java
@OneToMany(mappedBy="customer", cascade=CascadeType.PERSIST)
private Set<Order> orders;
```

#### `CascadeType.REMOVE`

- This cascade type specifies that the remove operation should be cascaded to the child entities. This means that whenever a remove operation is executed on the parent entity, the remove operation is also cascaded to the child entities. However, updates made to the parent entity will not be cascaded to the child entities.

```java
@OneToMany(mappedBy="customer", cascade=CascadeType.REMOVE)
private Set<Order> orders;
```

##### `CascadeType.REMOVE` vs `OrphanRemoval = true`

- `CascadeType.REMOVE` - **When you delete the parent entity,** the child entity will also be deleted.
    - Example: If you delete a Department entity that has employees associated with it, using `CascadeType.REMOVE` would automatically delete all related Employee entities.
- `OrphanRemoval = true` - **When you remove or disassociate the child entity from the parent entity,** the child entity will be deleted.
    - A child entity is considered an orphan if it's removed from the parent’s collection or if its relationship with the parent is broken (e.g., setting the reference to null).
    - Example: If an Employee is removed from the employees list in a Department entity, `orphanRemoval = true` would automatically delete that Employee from the database, even if the Department itself is not deleted.
    ```java
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;
    ```

--------------------------------------------

## Fetch Types

- **Fetch Types** define how JPA loads the related entities when querying the database.

- All `to-one` associations use `FetchType.EAGER` and all `to-many` associations `FetchType.LAZY` by default.
    - You can override the default by setting the fetch attribute of the `@OneToMany`, `@ManyToOne`, `@ManyToMany`, and `@OneToOne `annotation.

### `FetchType.EAGER` - Fetch it so you’ll have it when you need it

- **Eager fetching** means that when you fetch an entity, JPA will also fetch all its related entities from the database and load them into memory. This can be useful when you know you’ll need the related entities and want to avoid additional database queries.

![Eager Fetch](./imgs/eager-fetch.png)

- **Eager fetching** can lead to performance issues if you fetch too many entities at once. If you have a large number of related entities, eager fetching can result in a large amount of data being loaded into memory, which can slow down your application.

### `FetchType.LAZY` - Fetch it only when you need it

- **Lazy fetching** means that when you fetch an entity, JPA will only fetch the entity itself from the database. The related entities are not loaded into memory until you explicitly call a method to access them.

![Lazy Fetch](./imgs/lazy-fetch.png)

- **Lazy fetching** can becoem a performance issue if you access the related entities frequently. Each time you access a related entity, JPA will need to fetch it from the database, which can result in multiple database queries. This is known as the **N+1 query/select problem.**

##### N+1 Select Problem

- The n+1 select problem occurs when an application loads an entity and then lazily loads its associated entities in separate queries, causing unnecessary database calls and impacting performance.

- Here’s how it works:
    - When you retrieve a main entity (e.g., an `Order`), a single query is executed for that entity.
    - For each associated entity (e.g., `Items` in the Order), an additional query is executed to fetch each relation.
- Example:
    - **Scenario**: You have an `Order` entity with 5 associated entities (e.g., `Items`, `Customer`, `Address`, etc.).
    - **Queries**: This results in:
        - **1 query** to fetch the `Order`
        - **5 additional queries** to fetch each associated entity (1 for each relation).
        - **Total = 1 + 5 = 6 queries**

    If 100 users make requests in parallel:
    - **Total queries = 100 * (1 main query + 5 additional queries)** = **100 * 6 = 600 queries**
    This exponential growth in queries is the essence of the *n+1 select problem*, where performance degradation becomes increasingly significant as the number of associations and users grows so that the application becomes slow.

##### Avoiding the N+1 Select Problem

- You can use **JOIN FETCH** in JPQL queries to fetch the related entities in a single query. This can help avoid the n+1 select problem by fetching all the related entities in one go.

- **Example**:
    ```java
    public class Order{
        ...
        ...
        @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
        private List<Item> items;
    }

    // JPQL query with JOIN FETCH with lazy-loaded associations
    @Query("SELECT o FROM Order o JOIN FETCH o.items WHERE o.id = :orderId")
    Order findOrderWithItems(@Param("orderId") Long orderId);
    ```
--------------------------------------------