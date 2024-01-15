# Beans

A bean is an object that is instantiated, assembled, and managed by the **Spring IoC container**. One of the key features of the Spring container is its ability to manage the lifecycle of beans, which includes creating, configuring, and destroying beans as necessary.

Beans in Spring Boot are simply Java objects managed by the Spring framework. The annotations help identify which classes should be treated as beans, and Spring takes care of their creation and management.

In Spring Boot, every annotation that is part of the Spring framework's stereotype annotations, such as `@Component`, `@Service`, `@Repository`, and `@Controller`, among others, is used to define a bean.

When you annotate a class with one of these annotations, you are essentially telling the Spring framework that the class should be treated as a bean. The Spring container then manages the lifecycle of these beans, including creating instances, injecting dependencies, and handling any necessary configuration.

It's worth noting that Spring Boot also provides additional annotations for specific purposes, such as `@Configuration` for defining configuration classes and `@Autowired` for dependency injection. While these annotations are not specifically stereotype annotations, they work in conjunction with the stereotype annotations to define and manage beans in a Spring Boot application.

----------------------------------------

## Bean Creation

You can create beans by annotating a class with one of the stereotype annotations provided by the Spring framework. The most commonly used annotations are `@Component`, `@Service`, `@Repository`, and `@Controller`, depending on the purpose of your bean.

```java
@Component
public class MyBean {
    // Class implementation
}
```

In this example, the `@Component` annotation tells Spring that the `MyBean` class should be treated as a bean. Spring will then create an instance of this class and manage its lifecycle.

----------------------------------------

## Bean Names

Every bean has a name, which is used to identify it in the application context. By default, the name of a bean is the name of the class with the first letter in lowercase. For example, if you have a class called `MyBean`, the name of the bean will be `myBean`.

To change the bean name, you can specify a custom name using the `value` attribute of the annotation. Here's an example:

```java
@Component("myCustomBeanName")
public class MyBean {
    // Class implementation...
}
```

In this example, the bean name is set to "myCustomBeanName". You can then refer to this bean using its custom name when autowiring or injecting dependencies.

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

The `@Qualifier` annotation is used to specify the name of the bean to be injected. In this case, we provide the custom bean name "myCustomBeanName" as the qualifier.

By using the custom bean name and the `@Qualifier` annotation, you can control the name of the bean and ensure that the correct bean is injected when multiple beans of the same type are available in the application context.

----------------------------------------

## Bean Scopes

The scope of a bean can be specified in the configuration file using the scope attribute of the bean element.

It is very important to choose the right scope for a bean, as it can affect the behavior and performance of the application.

| Scope | Description |
| ----- | ----------- |
| Singleton | Default scope. Only one instance of the bean is created, shared by all requesting objects. |
| Prototype | A new instance of the bean is created each time it is requested. |
| Request | This is same as prototype scope, however it’s meant to be used for web applications. A new instance for each HTTP request. Available only in a web-aware Spring application. |
| Session | A new instance for each HTTP session. Available only in a web-aware Spring application. |
| Application | A single instance for the entire lifecycle of the application(ServletContext). Available only in a web-aware Spring application. |
| Web Socket | A new instance for each WebSocket connection. Available only in a web-aware Spring application. |

### Singleton Beans

- This is the default scope in Spring.

- In the singleton scope, Spring creates a **single instance** of the bean per container and shares that instance throughout the application.

- Every time a bean with a singleton scope is requested, the same instance is returned.

- By default, all beans in Spring Boot are singleton beans. This means that if you annotate a class with `@Component`, `@Service`, `@Repository`, or `@Controller`, the bean will be a singleton bean.

**Example:**

```java
@Configuration
public class MyConfiguration {
	
	@Bean
	@Scope(value="singleton")
    public MyBean myBean() {
		return new MyBean();
	}
	
}
```

### Prototype Beans

In this scope, Spring creates a new instance of the bean every time it is requested. Each instance is independent of the others and has a separate lifecycle.

To create a prototype bean, you can use the `@Scope` annotation and set the scope to `prototype`. Here's an example:

**Example:**

```java
@Configuration
public class MyConfiguration {
	
	@Bean
	@Scope(value="prototype")
    public MyBean myBean() {
		return new MyBean();
    }
}
```
----------------------------------------

## Multiple Beans of the Same Type

In a Spring Boot application, there can be multiple types of beans when you have multiple implementations or configurations for a particular interface or class. Let's consider an example:

Suppose you have an interface called `NotificationService`, which is responsible for sending notifications. Now, you want to have two different implementations of this interface: `EmailNotificationService` and `SMSNotificationService`.

To achieve this, you can create two separate classes implementing the `NotificationService` interface. For example:

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