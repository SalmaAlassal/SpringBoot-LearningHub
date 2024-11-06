# APIs (Application Programming Interfaces)

## What is an API?

API is a fundamental tool in modern software development that enables different applications to work together, share data, and provide services. It simplifies the process of building software by allowing developers to leverage the functionality of existing systems, services, and libraries.

API is like a waiter in a restaurant. The waiter is the one who takes your order, tells the kitchen what to cook, and then brings your food back to you. The API is the waiter who takes your order and tells the kitchen what to do. The kitchen then does the work and gives the food back to the waiter, who then brings it back to you.

So, an API is an intermediary that allows one peace of software to access the functionality of another. 

APIs work through a request and response mechanism. The requesting software sends a request to the API with specific parameters and instructions. The API processes the request and sends back a response with the requested data or performs the requested action.

### Examples

- **Google Maps API**: 
    - Enables developers to integrate Google Maps into their applications
- **PayPal API**: 
    - Allows businesses to integrate PayPal's payment processing capabilities into their websites or mobile apps, enabling online payments.
- **YouTube API**: 
    - Allows developers to integrate YouTube's functionality into their applications, such as uploading videos, searching for videos, and playing videos.

--------------------------------------------------------------------------------

## Why to use APIs?

- **Saves time and money**: 
    - Instead of building a feature from scratch, you can use an API to add it to your application.

- **Improves user experience**:
    - APIs allow you to add features to your application that would otherwise be too expensive or time-consuming to build from scratch.

--------------------------------------------------------------------------------

## Types of APIs

### SOAP APIs (Simple Object Access Protocol)

- These APIs use Simple Object Access Protocol. Client and server exchange messages using **XML.**

- They are less flexible APIs that was more popular in the past.

### JSON-RPC and XML-RPC APIs (Remote Procedure Call)

- These APIs use Remote Procedure Call. Client and server exchange messages using **JSON** or **XML.**

- The client completes a function (or procedure) on the server, and the server sends the output back to the client.

### Websocket APIs

- Websocket APIs are another modern web APIs development that use **JSON** objects to pass data. 

- WebSockets provide full-duplex, bidirectional communication channels over a single TCP connection between client and server. The server can send callback messages to connected clients, making it more efficient than REST API.

- They are used for real-time applications, such as chat apps and online games.

### REST APIs (Representational State Transfer)

- These are the most popular and flexible APIs found on the web today.

- REST is not a protocol but an architectural style. It can work with several different data formats like **JSON, XML, and plain text.**

### REST API vs Websocket API

- **Communication Pattern**:
    - **REST APIs** are based on the client-server model, where the client sends a request to the server, and the server sends a response back to the client.
    - **Websocket APIs** are based on the publish-subscribe model, where the server can send messages to the client without the client having to request them. It allows for real-time communication between the client and server.

- **State**:
    - **REST APIs are stateless,** meaning each request from the client to the server is independent. The server doesn’t retain any information about past requests, making it ideal for simpler, more modular systems.
    - **Websocket APIs are stateful,** where once a connection is established, it remains open and maintains context until it's closed by either party.

- **Use Cases**:
    - **REST APIs** are suitable for CRUD (Create, Read, Update, Delete) operations and are ideal for web applications that require data retrieval and manipulation.
    - **Websocket APIs** are ideal for real-time applications like chat apps, online games, collaborative editing tools(like Google Docs), and live streaming services.

--------------------------------------------------------------------------------

## API Scope

- **Public APIs**: 
    - These are open to the public and may be used by anyone. There may or not be some authorization and cost associated with these types of APIs. They also may have limits on the number of requests you can make.

- **Partner APIs**:
    - These are only accessible by authorized external developers to aid business-to-business partnerships.

- **Private APIs**:
    - These are internal to an enterprise and only used for connecting systems and data within the business.

--------------------------------------------------------------------------------

## API Security

APIs are vulnerable to attacks, so it is important to secure them. The two main ways to secure **REST APIs include:**

### 1. Authentication Tokens

- These are used to authorize users to make the API call. Authentication tokens check that the users are who they claim to be and that they have access rights for that particular API call.

### 2. API Keys

- API keys verify the program or application making the API call. They identify the application and ensure it has the access rights required to make the particular API call.

- API keys are not as secure as tokens but they allow API monitoring in order to gather data on usage. 

- You may have noticed a long string of characters and numbers in your browser URL when you visit different websites. This string is an API key the website uses to make internal API calls. 

--------------------------------------------------------------------------------