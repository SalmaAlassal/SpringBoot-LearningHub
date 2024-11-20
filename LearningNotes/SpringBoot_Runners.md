# Spring Boot Runners

- Runner interfaces lets you to execute the code after the Spring Boot application is started. You can use these interfaces to perform any actions immediately after the application has started.

- Spring Boot provides `ApplicationRunner` and `CommandLineRunner` interfaces to run the code after the application is started.

- Both of them provides the same functionality and the only difference between` CommandLineRunner` and `ApplicationRunner` is `CommandLineRunner.run()` accepts **String array[]** whereas `ApplicationRunner.run()` accepts **ApplicationArguments** as argument.

- Both `ApplicationRunner` and `CommandLineRunner` interfaces have a single `run` method that you have to implement.

## ApplicationRunner

```java
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner is running");
    }
}
```

## CommandLineRunner

```java
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner is running");
    }
}
```