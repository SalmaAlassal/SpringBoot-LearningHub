## Spring Boot Profiles

- Spring Boot profiles allow you to define sets of configurations that can be activated based on different environments or conditions. They help in managing application settings such as database configurations, logging levels, or other properties tailored specifically for development, testing, staging, or production environments.

- By activating different profiles, you can control how your Spring Boot application behaves, making it versatile and adaptable to different deployment environments or use cases.

- Profiles are defined using the `application-{profile}.properties` or `application-{profile}.yml` file naming convention. The `{profile}` placeholder is replaced with the actual profile name, such as `application-dev.properties` or `application-prod.yml`.
    - Examples:
        - `application.properties` for common configuration
        - `application-dev.properties` for development
        - `application-prod.properties` for production

> If the same property is specified in the profile-specific (`application-prod.properties`) configuration and in the common (`application.properties`) configuration, the value from the profile-specific configuration will take precedence, so in our scenario — the value from `application-prod.properties`.

### Activating Profiles

- Profiles can be activated in several ways, one of which is by setting the `spring.profiles.active` property in the `application.properties` file.
    - Example: `spring.profiles.active=dev,sim`

### Get Value from Profile

- To get the value of a property from a specific profile, you can use the `Environment` object provided by Spring Boot.

```java
private final Environment environment;
String propertyValue = environment.getProperty("property.name");
```