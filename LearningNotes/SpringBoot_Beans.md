# Beans

- A bean is a Java object managed by the Spring IoC container. Bean is an object that we can inject in any class and we need not worry about its instantiation or its dependencies as that is taken care by the framework itself.

- So bean is a predefined, preconfigured and pre-instantiated object of a certain class.

- The beans in Spring are stored in an IoC container which is often referred as **Application Context**.

- One of the key features of the Spring container is its ability to manage the lifecycle of beans, which includes creating, configuring, and destroying beans as necessary.

- In Spring Boot, every annotation that is part of the Spring framework's stereotype annotations, such as `@Component`, `@Service`, `@Repository`, and `@Controller`, among others, is used to define a bean.

- When you annotate a class with one of these annotations, you are essentially telling the Spring framework that the class should be treated as a bean. The Spring container then manages the lifecycle of these beans, including creating instances, injecting dependencies, and handling any necessary configuration.
    > There are older way to define beans in Spring using XML configuration. But with the introduction of annotations, the XML configuration is not used much.

- It's worth noting that Spring Boot also provides additional annotations for specific purposes, such as `@Configuration` for defining configuration classes and `@Autowired` for dependency injection. While these annotations are not specifically stereotype annotations, they work in conjunction with the stereotype annotations to define and manage beans in a Spring Boot application.

- There are 3 ways to provide beans configuration:
    - XML Configuration
    - Annotation-based Configuration
    - Java-based Configuration
    > XML configurations override the annotation-based configurations if you have both.

----------------------------------------

## Bean Creation

You can create beans by annotating a class with one of the stereotype annotations provided by the Spring framework. The most commonly used annotations are `@Component`, `@Service`, `@Repository`, and `@Controller`, depending on the purpose of your bean.

```java
@Component
public class MyBean {
    // Class implementation
}
```

In this example, the `@Component` annotation tells Spring that the `MyBean` class should be treated as a bean. Spring will then create an instance of this class and store it in the application context and manage its lifecycle.

----------------------------------------

## Bean Definition

- Spring framework provides represents beans as `BeanDefinition` objects.

- A `BeanDefinition` object has several properties that define the characteristics of a bean, such as its class, scope, name, properties...etc.

### `class` Property

- `class` property of the bean is the fully qualified name of the class that IOC container will instantiate.

- When the container instantiates a bean from a class it populates the class property of that bean with the fully qualified name we have provided.

- For example, if we have a class `MyBean` in the package `com.example`, then the class property of the bean will be `com.example.MyBean`.

### `properties` Property

- The properties property of the bean is populated from the properties of the class.

- For example,  If we have used a built-in type such as an `int` or a `string`, the container converts the property of our class into the same type of property for the bean. However, if we have used a custom type, such as `Animal` or `Driver`, this is a dependency and the container now has to create a `BeanDefinition` object for each of these types as well.
    > When a class encapsulates other objects, the referenced objects become a dependency for the outer class. In other words, these other objects must be created so the outer class can use it. The container takes a look at our classes and, depending on the method you choose, instantiates beans from the referenced objects before it instantiates a bean from the outer classes. This process is known as dependency injection; Our classes no longer have to instantiate their own dependencies. Therefore, we can say the control of dependencies has been inverted back to the container, and this is why we call it an Inversion of Control (IoC) container.

### `name` Property

- The name property of the bean is the name of the bean in the application context and is used to identify the bean.

- By default, the name of a bean is the name of the class with the first letter in lowercase. For example, if you have a class called `MyBean`, the name of the bean will be `myBean`.
    > Bean names start with a lowercase letter, and are camel-cased from then on.

- To change the bean name, you can specify a custom name using the `value` attribute of the annotation. Here's an example:

    ```java
    @Component("myCustomBeanName")
    public class MyBean {
        // Class implementation...
    }
    ```
    In this example, the bean name is set to `myCustomBeanName`. You can then refer to this bean using its custom name when autowiring or injecting dependencies.

    ```java
    @Service
    public class MyService {
        private final MyBean myBean;

        public MyService(@Qualifier("myCustomBeanName") MyBean myBean) {
            this.myBean = myBean;
        }
        
        // Class implementation...
    }
    ```

    The `@Qualifier` annotation is used to specify the name of the bean to be injected. In this case, we provide the custom bean name `myCustomBeanName` as the qualifier.

    By using the custom bean name and the `@Qualifier` annotation, you can control the name of the bean and ensure that the correct bean is injected when multiple beans of the same type are available in the application context.

### `scope` Property

- It is very important to choose the right scope for a bean, as it can affect the behavior and performance of the application.

| Scope     | Description |
| --------- | ----------- |
| Singleton | **Default scope.** Only one instance of the bean is created per container, shared by all requesting objects. |
| Prototype | A new instance of the bean is created each time it is requested.                                             |

```java
@Component
// @Scope("singleton") // Default scope
@Scope("prototype")
public class MyBean {
    // Class implementation...
}
```

**Available only in a web-aware Spring application:**

| Scope      | Description |
| ---------- | ----------- |
| Request    | A new instance for each HTTP request.|
| Session    | A new instance for each HTTP session. |
| Application| A single instance for the entire lifecycle of the application(ServletContext).|
| Web Socket | A new instance for each WebSocket connection. |

----------------------------------------

## Multiple Beans of the Same Type

- There can be multiple types of beans when you have multiple implementations or configurations for a particular interface or class.

- Suppose you have an interface called `NotificationService`, which is responsible for sending notifications. Now, you want to have two different implementations of this interface: `EmailNotificationService` and `SMSNotificationService`. To achieve this, you can create two separate classes implementing the `NotificationService` interface. For example:

```java
public interface NotificationService {
    void sendNotification(String message);
}

@Component
public class EmailNotificationService implements NotificationService {
    public void sendNotification(String message) {
        // Implementation for sending an email notification
    }
}

@Component
public class SMSNotificationService implements NotificationService {
    public void sendNotification(String message) {
        // Implementation for sending an SMS notification
    }
}
```
 
> The bean type in Spring represents the class or interface that a bean belongs to.

In this case, you have defined two beans of type `NotificationService`: `EmailNotificationService` and `SMSNotificationService`. Each of these beans provides a different implementation for the same interface.

Now, when you have a class that depends on the `NotificationService`, you can use the `@Autowired` annotation to automatically inject the appropriate implementation. For example:

```java
@Component
public class NotificationHandler {
    private final NotificationService notificationService;

    @Autowired
    public NotificationHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Use the notificationService to send notifications
}
```

By using the `@Autowired` annotation, Spring Boot will automatically inject the correct bean based on the type. If you have not specified any `@Qualifier` or `@Primary`, and there are multiple beans of the same type, Spring Boot may encounter ambiguity and throw an exception. In such cases, you can use the `@Qualifier` annotation to specify a particular bean to be injected.

So, the presence of multiple types of beans arises when you have multiple implementations or configurations for an interface or class, allowing you to choose and inject the appropriate implementation based on your needs.

To resolve the potential ambiguity when there are multiple beans of the same type, you can use the `@Qualifier` annotation to specify which bean should be injected. Here's how you can modify the code to address this:

```java
@Component
@Qualifier("emailNotification")
public class EmailNotificationService implements NotificationService {
    public void sendNotification(String message) {
        // Implementation for sending an email notification
    }
}

@Component
@Qualifier("smsNotification")
public class SMSNotificationService implements NotificationService {
    public void sendNotification(String message) {
        // Implementation for sending an SMS notification
    }
}
```

In the above code, we added `@Qualifier` annotations with unique identifiers to each implementation of the `NotificationService`.

Next, you can use the `@Qualifier` annotation along with `@Autowired` in the dependent class to specify which bean should be injected. Here's an updated version of the `NotificationHandler` class:

```java
@Component
public class NotificationHandler {
    private final NotificationService emailNotificationService;
    private final NotificationService smsNotificationService;

    @Autowired
    public NotificationHandler(@Qualifier("emailNotification") NotificationService emailNotificationService,
                               @Qualifier("smsNotification") NotificationService smsNotificationService) {
        this.emailNotificationService = emailNotificationService;
        this.smsNotificationService = smsNotificationService;
    }

    // Use the emailNotificationService and smsNotificationService to send notifications
}
```

By using `@Qualifier` along with `@Autowired`, we explicitly specify which bean should be injected for each dependency.

With these changes, when `NotificationHandler` is instantiated, the appropriate implementations (`EmailNotificationService` and `SMSNotificationService`) will be injected based on the qualifiers.

This approach allows you to differentiate between multiple beans of the same type and ensure that the correct beans are injected based on your requirements.

----------------------------------------