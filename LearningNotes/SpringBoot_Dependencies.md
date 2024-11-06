# My Spring Boot Project Dependencies

This document provides an overview of the dependencies used in my Spring Boot projects. I've compiled this list based on the dependencies I've used in my projects until now. I will keep it updated as I introduce new dependencies or make changes to existing ones in my projects.

## Contents

- [Spring Boot Starter Dependencies](#spring-boot-starter-dependencies)
    - [Spring Boot Web](#spring-boot-web)
    - [Spring Boot Data JPA](#spring-boot-data-jpa)
- [Database Dependencies](#database-dependencies)
    - [H2 Database](#h2-database)
- [Security Dependencies](#security-dependencies)
    - [Spring Boot Security](#spring-boot-security)
    - [JWT (JSON Web Token)](#jwt-json-web-token)
- [Additional Dependencies](#additional-dependencies)
    - [Lombok](#lombok)
    - [Thymeleaf](#thymeleaf)


## Spring Boot Starter Dependencies 

### Spring Boot Web

This dependency is used to build web applications using Spring MVC. It provides all the necessary components for building web applications, including embedded Tomcat.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

### Spring Boot Data JPA

This dependency is used to build Spring Data JPA repositories. It simplifies database operations and entity management using JPA.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

### Spring Boot Webflux

This dependency is used to build reactive web applications using Spring Webflux. It provides the necessary libraries, configurations, and tools to develop web applications that can handle asynchronous and non-blocking operations efficiently.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
    <version>3.1.3</version>
</dependency>
```

----------------------------------------------

## Database Dependencies

### H2 Database

This dependency is used to build in-memory databases for development and testing. It is great for rapid prototyping and testing without requiring a separate database server.

```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
</dependency>
```
----------------------------------------------

## Security Dependencies

### Spring Boot Security

This dependency is used to secure Spring Boot applications. It provides a set of servlet filters that can be used to secure web applications. It also provides a set of annotations that can be used to secure methods and classes.

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-security</artifactId>
	<version>2.6.10</version>
</dependency>
```

### JWT (JSON Web Token)

This dependency is used to generate and verify JSON Web Tokens (JWTs). It is used to generate JWTs for authentication and authorization purposes.

```xml
<dependency>
	<groupId>io.jsonwebtoken</groupId>
	<artifactId>jjwt</artifactId>
	<version>0.9.1</version>
</dependency>
```

----------------------------------------------

## Additional Dependencies

### Lombok

This dependency is used to reduce boilerplate code in Java classes. It provides annotations that can be used to generate getters, setters, constructors, and more.

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```

### Thymeleaf

This dependency is used to build web applications using Thymeleaf. It provides all the necessary components for building web applications, including embedded Tomcat.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

----------------------------------------------


