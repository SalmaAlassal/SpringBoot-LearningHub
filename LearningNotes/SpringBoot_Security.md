# Spring Security

- Spring Security is a framework for securing Java-based applications at various layers with great
flexibility and customizability.

- Spring Security provides authentication and authorization support against database authentication,
LDAP, form authentication, JA-SIG central authentication service, Java Authentication and Authorization Service (JAAS), and many more.

- Spring Security provides support for dealing with common attacks like CSRF, XSS, and session fixation
protection, with minimal configuration.

- Spring Security can be used to secure the application at various layers, such as web URLs, service layer
methods, etc.

- In order to use Spring Security in the Spring Boot project, we need to add the below Maven dependency:
    
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    ```
    Once the dependency is added, Spring Security is enabled by default, it enables **form-based** and **HTTP basic** authentication.


- Adding the Spring Security Starter `spring-boot-starter-security` to an Spring Boot application will:-
    - Enable HTTP basic authentication
    - Register the `AuthenticationManager` bean with an in-memory store and a single user
    - Ignore paths for commonly used static resource locations (such as `/css/**`, `/js/**`, `/images/**`, etc.)
    - Enable common low-level features such as XSS, CSRF, caching, etc.


- There are different ways to configure Spring Security in a Spring Boot application:
    - **Java Configuration** (using `@Configuration` classes) - **Recommended**
    - **XML Configuration**

- The data like username, password, roles, etc., need to be stored somewhere. Spring Security provides various ways to work with user data that needs to be authenticated and authorized in the application. Some of the ways to store user data are:
        - **In-Memory**
        - **JDBC**
        - **LDAP**
        - **Custom**

![Spring Security](./imgs/Spring_Security.png)



<!-- ## Form-Based Authentication vs Basic Authentication -->
<!-- https://medium.com/@haytambenayed/basic-authentication-and-form-based-authentication-using-spring-security-ed79951dbb2e -->

<!-- https://www.javaguides.net/2023/04/difference-between-basic-auth-and-form-based-auth.html -->

## Basic Authentication

- **Basic Authentication** is a simple authentication mechanism that requires a username and password to access a resource. **It is a stateless authentication mechanism**, meaning that each request must include the user's credentials.

- Basic Auth uses an HTTP header in order to provide the username and password when making a request to a server.

- Basic Auth uses **Base 64** encoded username and password in the header.

- Basic Authentication **DO NOT use cookies**, hence there is no concept of a session or logging out a user, which means each request has to carry that header in order to be authenticated.

![Basic Authentication](./imgs/Basic_Authentication.png)


## Security Concepts

### Authentication

- **Authentication** is the process of verifying the identity of a user. It answers the question, **"Who are you?"**
    > Check if you're a user or not. Typically, this is done by providing a username and password.

<!-- - Spring Security supports various authentication mechanisms, such as **form-based login**, **OAuth2**, and more, without relying on deprecated classes. -->

### Authorization

- After authentication, **authorization** determines whether the authenticated user has permission to perform a given action or access a resource. It answers the question, **"Are you allowed to do this?"**


### Session Authentication

- **Session Authentication** is a **stateful authentication technique** where we use sessions to keep track of the authenticated user across multiple requests.

- It uses a **session cookie** to store the user's identity.

- Here's how it works:
    - User submits the login request for authentication.
    - Server validates the credentials. If the credentials are valid, the server initiates a session and stores some information about the client. This information can be stored in memory, file system, or database. The server also generates a unique identifier (session id) that it can later use to retrieve this session information from the storage. Server sends this unique session identifier to the client.
    - Client saves the session id in a cookie and this cookie is sent to the server in each request made after the authentication.
    - Server, upon receiving a request, checks if the session id is present in the request and uses this session id to get information about the client.

![Session Authentication](./imgs/Session_Authentication.png)

### Token Authentication

- **Token Authentication** is a way to authenticate users using tokens. A token is a piece of data that is used to authenticate a user. It is generated by the server and sent to the client. The client then sends this token in the header of each request to authenticate itself.

- Here's how it works:
    - User submits the login request for authentication.
    - Server validates the credentials. If the credentials are valid, the server generates a token and sends it to the client.
    - Client saves the token and sends it in the header of each request made after the authentication.
    - Server, upon receiving a request, checks if the token is present in the header and validates it.

![Token Authentication](./imgs/Token_Authentication.png)