# Thymeleaf

Thymeleaf is a popular server-side Java template engine that allows you to build dynamic web pages. It is designed to be a natural and elegant way to create HTML templates with server-side Java code integration. Thymeleaf provides a powerful and flexible templating syntax that enables you to create dynamic content, manipulate data, and control the rendering of web pages.

You should put html files in the `src/main/resources/templates` folder.

--------------------

# H2 Database

H2 is an open-source in-memory database that can be used with Spring Boot applications. It provides a lightweight and fast database solution that is particularly useful for development, testing, and prototyping purposes.

H2 database can be easily integrated into a Spring Boot application by adding its dependency in the project configuration. It supports both in-memory and persistent modes, allowing you to store data in memory during runtime or persist it to disk for long-term storage.

When using H2 with Spring Boot, you can define the database connection details, such as URL, username, and password, in the application configuration file. Spring Boot's auto-configuration feature automatically configures the H2 database based on these settings.

One of the advantages of H2 is its simplicity and ease of use. It provides a web-based console called H2 Console that allows you to interact with the database, execute SQL queries, and perform administrative tasks. The H2 Console is accessible through a browser, which makes it convenient for monitoring and managing the database during development.

Furthermore, H2 supports standard SQL syntax and provides various features like transactions, indexes, functions, and triggers.

### Notes

- Hibernate automatically converts entity names to lowercase unless you specify a different naming strategy.

--------------------

# Spring Data JPA

Spring Boot JPA is a part of the Spring Boot framework that helps Java developers work with databases in an easier way. 

JPA stands for Java Persistence API, which is a set of rules and guidelines for storing and retrieving data in Java applications. Spring Boot JPA makes it easier to use these rules in your application.

Spring Data JPA is a powerful library that implements the data access layer for Spring applications. It allows you to create repositories for performing CRUD (Create, Read, Update, Delete) operations on entities.

Spring Data JPA provides a set of annotations that can be used to define the mapping between entities and database tables. It also provides a set of interfaces that can be used to create repositories for performing CRUD operations on entities.

# JPARespository vs CrudRepository

The `JpaRepository` interface extends `ListCrudRepository`, `PagingAndSortingRepository`, and `QueryByExampleExecutor` interfaces making it have more methods than the `CrudRepository` interface.

--------------------