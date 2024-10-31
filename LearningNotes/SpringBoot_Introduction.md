# Spring Boot - Introduction

## What is Spring Boot?
 
Spring Boot is an open source Java-based framework used to create a micro Service. It's used to build stand-alone and production ready spring applications. It's a Spring module that provides the RAD (Rapid Application Development) feature to the Spring framework. It enables the developers to directly focus on the logic instead of struggling with the configuration and set up.

Spring Boot is the combination of Spring Framework and Embedded Servers.

![Spring Boot](imgs/what-is-spring-boot.png)

## What is Framework?

- A framework is a collection of libraries, classes, and tools that provide a foundation for developing software applications.

- With the help of a framework, you can avoid writing everything from scratch. Frameworks provide a set of tools and elements that help in the speedy development process.

## What is Micro Service?

- Microservices are a software architectural style in which a large application is built as a collection of small, independent services that communicate with each other over a network.

- Each service is a self-contained unit of functionality that can be developed, tested, and deployed independently of the other services.

- Microservices can be written in different programming languages and use different technologies, as long as they can communicate with each other through a common API.

- They are designed to be **loosely coupled**, meaning that changes to one service should not affect the other services. This makes it easier to update, maintain, and scale the application.

- Spring Boot is a popular framework for building microservices in the Java ecosystem.

## Advantages of Spring Boot

- **Stand-alone Applications**:
   - **Stand-alone Application**: means an application that can run independently without relying on an external application server.
   - Spring Boot applications are typically packaged as JAR files (Java ARchives) that include everything they need to run: the application code, libraries, and an embedded web server (like Tomcat, Jetty, or Undertow).
   - You can run a Spring Boot application by simply running the JAR file using the `java -jar` command.

- **Embedded Server Support:**
   - No need for external application servers; Spring Boot handles this internally.

- **Opinionated Starter POMs:**:
   - Spring Boot provides a set of starter POMs (Project Object Model) that include all the dependencies you need to build a particular type of application.
   - For example, if you want to build a web application, you can use the `spring-boot-starter-web` starter POM, which includes all the dependencies you need for web development like Spring MVC and Tomcat.

- **Production-Ready Features**:
   - Spring Boot includes a number of features that are useful for building production-ready applications, such as metrics, health checks, and externalized configuration.
   - These features are automatically configured for you, so you don't have to spend time setting them up yourself.

- **Reduced XML Configuration**:
   - Spring Boot eliminates XML configurations, relying on annotations and Java classes, which are more concise and expressive.

- **CLI Support**:
   - Spring Boot provides a Command Line Interface (CLI) that you can use to quickly create, test, and run Spring Boot applications from the command line.

- **Easy Integration:**
   - Integrates seamlessly with other frameworks like Hibernate, Thymeleaf, and JSP.

- **Reduces the development time and increases productivity.**

## Disadvantages of Spring Boot

- **Lack of control:**
   - Some unused dependencies may be included, leading to larger files. For instance, `spring-boot-starter-web` might pull in libraries you don’t need.
   - Some people prefer to work with spring and only include the dependencies they need.


## Spring Boot Entry Point

The entry point of the spring boot application is the class contains `@SpringBootApplication` annotation and the main method.

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```
-------------------------------------------