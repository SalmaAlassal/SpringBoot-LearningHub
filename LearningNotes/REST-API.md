# REST API (Representational State Transfer)

API developers can design APIs using several different architectures. APIs that follow the REST architectural style are called REST APIs. Web services that implement REST architecture are called RESTful web services. The term RESTful API generally refers to RESTful web APIs. However, you can use the terms REST API and RESTful API interchangeably.

## REST Constraints

Rest has 6 constraints, if any of these constraints is not satisfied then the API is not RESTful.

### 1. Client-Server

This constraint keeps the client and server **loosely coupled**.

The client-server constraint works on the concept of separating the user interface concerns from the data storage concerns. It means that the client and the server can evolve separately without any dependency on each other.

### 2. Stateless

Each request from a client to a server must contain all the information needed to understand and process the request. The server should not store any client state between requests. This means that every request should be independent, and the server should not rely on previous requests to understand the current one.

E.g. If a client makes an authenticated request, it should include its authentication token with each request. The server should not store this token or rely on previous requests to identify the client. Each request should carry the necessary authentication information.

### 3. Cacheability

Caching is the ability to store copies of frequently accessed data in several places along the request-response path. Any client or server can cache responses. Whenever a client requests a resource from the server, the server includes information about how the response can be cached. Then, the client caches the response. The next time the client needs the same resource, it uses the cached response instead of making a request to the server.

### 4. Uniform Interface

It is a key constraint that differentiate between a REST API and Non-REST API. It suggests that there should be an uniform way of interacting with a given server.

<!-- There are four interface constraints:

- **Resource Identification in Requests**: Each resource in the API must be uniquely identifiable using a resource identifier. For example, in a RESTful API for a library, each resource might be identified by a URI such as `https://api.example.com/v1/books`. In this example, `books` is the resource identifier.

- **Resource Manipulation through Representations**: A client can modify resources by sending representations to the server. A representation contains data in a format that the client understands, such as JSON. For example, a client can create a new book by sending a representation that contains the book's details to the server.

- **Self-descriptive Messages**: Each message includes enough information to describe how to process the message. For example, a message that contains JSON data might include information that specifies the JSON type.

- **Hypermedia as the Engine of Application State (HATEOAS)**: The server includes information about available actions and transitions in response to a request. For example, a response might include links that the client can follow to retrieve related resources. -->

### 5. Layered System

An application architecture needs to be composed of multiple layers. Each layer doesn’t know any thing about any layer other than that of immediate layer and there can be lot of intermediate servers between client and the end server. Intermediary servers may improve system availability by enabling load-balancing and by providing shared caches.

### 6. Code on Demand (Optional)

It allows a client to download and execute code from a server.

-----------------------------------------

## Resources

A RESTful resource is anything that is addressable over the web. By addressable, we mean resources that can be accessed and transferred between clients and servers.

A resource can be any object the API can offer info about. For example, a resource can be a user, a photo, a book, a song, a video, a comment, etc.

Each resource is identified by a unique identifier (URI). For example, a user resource can be identified by the URI `/users/{id}`.

Rest uses various representations to represent a resource. For example, a user resource can be represented in  Text, Json, XML, etc. The most popular representations of resources are XML and JSON.

-----------------------------------------

## REST API URI Naming Conventions

REST APIs use **Uniform Resource Identifiers (URIs)** to address resources. A URI is a string of characters that identifies a resource. URIs are also known as **URLs** (Uniform Resource Locators).

> In REST, the primary data representation is called resource. A resource can be any object the API can offer info about.

### Mantain Consistent Naming Conventions

Choose a naming convention for your URIs and stick to it. Consistency is critical for an API to be easily understood and used.

Choose either camelCase (/userGroups) or snake_case (/user_groups), but don't mix.

### Use nouns to name resources

```
/users
/products
/orders
```

### Use Plural for Collections

```
/device-management/managed-devices
/user-management/users
/user-management/users/{id}/accounts
/song-management/users/{id}/playlists/{id}/songs
```

### Use Singular for Single Elements

```
/users/{id}
/products/{id}
/device-management/managed-devices/{device-id}
/user-management/users/{id}
/user-management/users/admin
```

### Collection and Sub-collection Resources

A collection resource can have sub-collection resources.

```java
/customers						//is a collection resource

/customers/{id}/accounts		// is a sub-collection resource
/customers/{customerId}/accounts/{accountId}

/tickets/{id}/messages/{id}
```

### Avoid Special Characters and Spaces

Special characters and spaces can cause issues in URLs. If you need to separate words in a URI, use hyphens (`-`) or underscores (`_`).

**It's not a good practice to use _ (underscore) in URI.** It is possible that the underscore (_) character can either get partially obscured or completely hidden in some browsers or screens.

```
/managed-entities/{id}/install-script-location
```

### Use Lowercase Letters

URIs are generally **case-insensitive**, but it's a good practice to keep them consistently lowercase for simplicity and consistency.

```
/my-folder/my-doc
```

### Never use CRUD function names in URIs

```
HTTP GET     /device-management/managed-devices  			//Get all devices
HTTP POST    /device-management/managed-devices  			//Create new Device
HTTP GET     /device-management/managed-devices/{id}  		//Get device for given Id
HTTP PUT     /device-management/managed-devices/{id}  		//Update device for given Id
HTTP DELETE  /device-management/managed-devices/{id}  	    //Delete device for given Id
```

### Use query parameters for filtering

Often, you will encounter requirements where you will need a collection of resources sorted, filtered, or limited based on some specific resource attribute.

For this requirement, do not create new APIs – instead, enable sorting, filtering, and pagination capabilities in resource collection API and pass the input parameters as query parameters.

```
✅ /books?author=Rowling&sort=title
❌ /booksByRowlingSortedByTitle

GET /users?role=admin
```

### Implement Versioning (Optional, but recommended)

```
/v1/users
```

### Stick to Standard HTTP Methods

`POST` to create, `GET` to read, `PUT` to update (or create), `DELETE` to delete.

### Use HTTP Status Codes to Indicate Results

```
200 OK
201 Created
400 Bad Request
404 Not Found
...etc
```

### Field Name Casing Convention

If the request body or response type is JSON then follow camelCase to maintain consistency.

```
{
    "firstName": "John",
    "lastName": "Doe",
    "age": 25
}
```
-----------------------------------------