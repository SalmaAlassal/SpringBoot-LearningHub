# Spring Boot IOC Container

Spring Boot uses the Spring Framework’s **Inversion of Control (IoC) container** to manage objects and their dependencies. The IoC container is responsible for creating objects, wiring them together, and managing their lifecycle. When an object is created, its dependencies are also created and injected into the object.

## IOC Principle

- **Inversion of Control (IoC) is a design principle** in which a software component is designed to receive its dependencies from an external source, rather than creating them itself.

- The control of object creation and lifecycle is transferred to a container or framework, which manages the dependencies and provides them to the component when needed.

## IOC Container

- Its the generic term given to the **container which manages the bean lifecycle in spring.**


- In normal programming paradigm, we use the new keyword to create an object. This creates dependency between the two classes.
    ```java
    public class Addation{
        public void add(int number){
            System.out.println("Added number : "+number);
        }
    }
    ```

    ```java
    public class Math{
        Addation addation=new Addation();
            
        public void addSomeNumber(int number){
            addation.add(number);
        }
    }
    ```
    In this example, the `Math` class directly depends on the `Addition` class and we have no control on the `Addition` class while creating a `Maths` class.
    
    In Inversion of Control, we do something like this :
    ```java
    public class Math{
        Addation addation;
        
        public void setAddation(Addation addation){
            this.addation=addation;
        }

        public void addSomeNumber(int number){
            addation.add(number);
        }
    }
    ```
    In this case, we are receiving the `addition` object of type `Addition` class through a setter method. This removes the dependency between `Math` class and `Addition` class.
    
    Now we have control over which `Addition` class implementation to use while instantiating the `Math` class.



- So instead of injecting the dependency of classes through the new keyword:
    ```java
    class A{
        B b=new B();
    }
    ```
    we transfer the control of object creation from java to IoC container. We use the `@Autowired` annotation to inject the dependency.
    ```java
    class A{
        @Autowired
        B b;
    }
    ```

- The IoC container is responsible to instantiate, configure and assemble the objects. The IoC container gets informations from the XML file and works accordingly.

- It is called inversion because the process is inverse where the bean creation and dependency is controlled by the container instead of the bean itself.

- The main tasks performed by IoC container are:
    - to instantiate the application class
        > instantiating the application class means creating the object of the class
    - to configure the object
        > configuring the object means setting the object's properties
    - to assemble the dependencies between the objects
        > assembling the dependencies means injecting the dependencies into the object.

- There are two types of IoC containers. They are:
    - **BeanFactory**: The BeanFactory is a basic IoC container that provides basic features such as bean instantiation and dependency injection.
    - **ApplicationContext**: The ApplicationContext is a more advanced IoC container that builds on top of the BeanFactory and provides additional features such as event publishing, messaging, and internationalization.

## Beans

- Beans are Java objects that are configured at run-time by Spring IoC Container.

- These beans are created with the configuration metadata that you supply to the container, for example, in the form of XML configuration, Java annotations, or Java code.


## Example

- In the below example, we don’t create the instance of `StudentRepository` manually, rather the spring creates it for us. Technically the Spring IoC Container creates it for us.

```java
@Service
public class StudentService {

  @Autowired
  private StudentRepository repository;
  // Other logics
}
```

```java
@Repository
public class StudentRepository {
    //persistence logic
}
```

## BeanFactory

- The BeanFactory interface is the **root interface** for accessing a Spring IoC container.

- It provides basic functionality for managing beans, such as instantiation, configuration, and dependency injection.

- BeanFactory implementations, such as `DefaultListableBeanFactory`, `LazyInitBeanFactory`, and `XmlBeanFactory`, are available in Spring.

## ApplicationContext

- The ApplicationContext interface extends the BeanFactory interface and provides additional functionality for managing beans and application context.

- ApplicationContext implementations, such as `ClassPathXmlApplicationContext`, `FileSystemXmlApplicationContext`, and `AnnotationConfigApplicationContext`, are available in Spring.

---------------------------------------------------

# Dependency Injection (DI)

## What is a Dependency?

- A dependency is any object or service that a class or component requires to perform its tasks correctly.

- It represents a relationship where one component relies on another to function properly.

- Dependencies can be other classes, objects, services, configurations, or resources that are used by a particular component.

- For example, if you have a class that sends an email, it will have a dependency on a class that provides the email service.

## What is Dependency Injection?

- Dependency injection is a design pattern used to implement IoC (Inversion of Control) principle.

- Dependency injection is a technique that allows us to remove the hard-coded dependencies and make our application loosely coupled, extendable, and maintainable.

## Types of Dependency Injection

- **Constructor Injection:** The injector supplies dependency through the client class constructor.
    > If you have only one constructor, there is no need to declare `@Autowired` over the Controller Constructor. It will detect and inject the necessary dependency.
- **Setter Injection:** The injector supplies dependency through a setter method of the client class.

- **Interface Injection:** The injector uses Interface to provide dependency to the client class. The clients must implement an interface that will expose a setter method which accepts the dependency.

### Constructor Injection

```java
public class NotificationClient {
    private final NotificationService notificationService;

    // Constructor injection
    // @Autowired (optional)
    public NotificationClient(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void sendNotification(String message) {
        // Using the injected service
        notificationService.sendNotification(message);
    }
}
```
### Setter Injection

```java
public class NotificationClient {
    private NotificationService notificationService;

    // Setter method for injection
    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void sendNotification(String message) {
        // Using the injected service
        notificationService.sendNotification(message);
    }
}
```
----------------------------------------------------------
