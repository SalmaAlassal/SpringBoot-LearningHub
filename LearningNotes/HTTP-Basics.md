# HTTP Basics

# Table of Contents

1. [HTTP Methods](#http-methods)
2. [HTTP Status Codes](#http-status-codes)
3. [MIME Types](#mime-types)
4. [HTTP Request Structure](#http-request-structure)
5. [HTTP Response Structure](#http-response-structure)
6. [Examples of Requests and Responses](#examples-of-requests-and-responses)


# HTTP Methods

- [GET](#get)
- [PUT](#put)
- [UPDATE](#update)
- [DELETE](#delete)
- [PATCH](#patch)

## GET

- **Description**: The GET method is used to retrieve information from the server. For example, retrieving a webpage, fetching JSON data, or accessing an image.

- **Request Format**: GET requests include parameters in the URL's query string, which are typically used to filter data. For example: `GET /api/users?name=John&page=2`

## POST

- **Description**: The POST method is used to create a new resource on the server. For example, creating a new user account.

- **Request Format**: POST requests include the resource to create in the request body. For example: `POST /api/users` with the new user data.

## PUT

- **Description**: The PUT method is used to update an existing resource on the server and if it doesn't exist, then it creates a new resource.

- **Request Format**: PUT requests include the updated resource representation in the request body. For example: `PUT /api/users/123` with the updated user data.

## DELETE

- **Description**: The DELETE method is used to delete a resource from the server.

- **Request Format**: DELETE requests include the resource to delete in the request body. For example: `DELETE /api/users/123` with the user to delete.

## PATCH

- **Description**: Unlike PUT Request, PATCH does **partial update** e.g. Fields that need to be updated by the client, only that field is updated without modifying the other field. The PATCH request only needs to contain the changes to the resource, not the complete resource.

- **Request Format**: PATCH requests include the updated resource representation in the request body. For example: `PATCH /api/users/123` with the updated user data.


# HTTP Status Codes

1. [Informational Responses (100 - 199)](#1-informational-responses-100---199)
2. [Successful Responses (200 - 299)](#2-successful-responses-200---299)
3. [Redirection Messages (300 - 399)](#3-redirection-messages-300---399)
4. [Client Error Responses (400 - 499)](#4-client-error-responses-400---499)
5. [Server Error Responses (500 - 599)](#5-server-error-responses-500---599)


## Response Status Code Categories

The first digit of the status code indicates the general category of the response.

There are five values for the first digit:
   - 1xx: Informational responses
   - 2xx: Successful responses
   - 3xx: Redirection messages
   - 4xx: Client error responses
   - 5xx: Server error responses

## 1. Informational Responses (100 - 199)

## 2. Successful Responses (200 - 299)

- **200 OK**: The request has succeeded.
    - E.g. The request was successful and the response body contains the requested data.

- **201 Created**: The request has been fulfilled and resulted in a new resource being created.
    - E.g. The request was successfully processed and a new resource was created.

- **204 No Content**: The server successfully processed the request and is not returning any content.
    - E.g. The request was successfully processed, but the response body is empty.

## 3. Redirection Messages (300 - 399)

## 4. Client Error Responses (400 - 499)

- **400 Bad Request**: The server could not understand the request due to client error, and the request cannot be processed.
    - E.g. The request is missing a required parameter.

- **403 Forbidden**: The server understood the request, but is refusing to fulfill it.
    - E.g. The client does not have permission to access the requested resource.

- **404 Not Found**: The server cannot find the requested resource.
    - E.g. The requested resource does not exist.

- **406 Not Acceptable**: The server cannot provide a response in a format acceptable to the client.
    - E.g. The client requests a xml response, but the server can only provide a json response.

## 5. Server Error Responses (500 - 599)

- **500 Internal Server Error**: It means that an error occurred on the server-side, and the server was unable to handle the request due to some internal issue.
    - E.g. Database connection error.
    - E.g. Division by zero.
    
    > The generic answer for an unexpected failure if there is no more specific information available.

------------------------------------------------------------

For each HTTP method, there are expected status codes that should be returned upon success.

- GET - return 200 (OK)
- POST - return 201 (Created)
- PUT - return 200 (OK)
- DELETE - return 204 (No Content)

------------------------------------------------------------

# MIME Types

Since HTTP/1.0, different types of content can be transmitted over the HTTP protocol. The type of content is specified by the MIME type **(Multipurpose Internet Mail Extensions)**.

The MIME type is sent in the HTTP header and indicates the type of content that is sent to the client.

### Structure

`type/subtype`

The MIME type consists of two parts: a type and a subtype. 
    - Type indicates the general category of the data,
    - while the subtype specifies the specific type of data.

Examples :

- `text/html` - HTML document
- `text/css` - CSS stylesheet
- `image/jpeg` - JPEG image
- `application/json` - JSON data
- `application/pdf` - PDF document
- `video/mp4` - MP4 video
- `audio/mpeg` - MP3 audio


An optional parameter can be added to provide additional details: `type/subtype;parameter=value`

For example, for any MIME type whose main type is text, you can add the optional `charset` parameter to specify the character set used for the characters in the data. If no charset is specified, the default is **ASCII (US-ASCII)** `text/html;charset=UTF-8`

MIME types are **case-insensitive** but are traditionally written in **lowercase**. The **parameter values** can be **case-sensitive**.

------------------------------------------------------------

# HTPP Request Structure

### Request Line

The first line of an HTTP request is called the request line. It contains the HTTP method, the resource path, and the HTTP version.

`<HTTP method> <resource path> HTTP/<version>`

For example: `GET /api/users HTTP/1.1`

### Request Headers

The request headers are included in the HTTP request to provide additional information about the request itself, the client, and the client's preferences.

#### Example Request Headers

- **Accept**: The MIME types accepted by the client. For example, `Accept: text/html, application/xhtml`

- **Accept-Language**: The natural languages accepted by the client. For example, `Accept-Language: en-US,en;q=0.5`

- **Authorization**: Contains credentials for authentication purposes. For example `Authorization: Basic dXNlcjE6cGFzc3dvcmQxMjM=`

### Request Body

The request body contains the data that is sent to the server. It is optional and only used for some HTTP methods.

------------------------------------------------------------

# HTTP Response Structure

### Status Line

The first line of an HTTP response is called the status line. It contains the HTTP version, the status code, and the status message.

`HTTP/<version> <status code> <status message>`

For example: `HTTP/1.1 200 OK`

### Response Headers

The response headers are included in the HTTP response to provide additional information about the response itself, the server, and instructions for handling the response.

#### Example Response Headers

- **Content-Type**: The MIME type of the response body. For example, `Content-Type: text/html; charset=UTF-8`

- **Content-Length**: The length of the response body in bytes. For example, `Content-Length: 1024`

- **Set-Cookie**: Set a cookie on the client. For example, `Set-Cookie: session=123456789`

### Response Body

The response body contains the data that is sent back to the client. It is optional and only used for some HTTP status codes.

------------------------------------------------------------

# Examples of Requests and Responses

### GET Request

```
GET /api/users HTTP/1.1
Host: example.com
Accept: application/json
```

### GET Response

```
HTTP/1.1 200 OK
Content-Type: application/json; charset=UTF-8
Content-Length: 1024

{
    "users": [
        {
            "id": 1,
            "name": "John Doe",
            "email": "
        },
        {
            "id": 2,
            "name": "Jane Doe",
            "email": "
        }
    ]
}

```

### POST Request

```
POST /login HTTP/1.1
Host: www.example.com
Content-Type: application/x-www-form-urlencoded
Content-Length: 32

username=johndoe&password=secret123
```

### POST Response

```
HTTP/1.1 200 OK
Date: Thu, 28 Oct 2021 15:20:33 GMT
Server: Apache/2.4.41 (Ubuntu)
Content-Length: 1270
Content-Type: text/html; charset=UTF-8

<!DOCTYPE html>
<html>
<head>
    <title>Welcome to Example.com</title>
</head>
<body>
    <h1>Hello, World!</h1>
</body>
</html>
```

------------------------------------------------------------

## Synchrounous vs Asynchronous HTTP Requests

- **Synchronous** HTTP requests are blocking or long-running operations that prevent the user from interacting with the application while the request is being processed.

- **Asynchronous** HTTP requests are non-blocking or short-running operations that allow the user to interact with the application while the request is being processed.

Asynchronous requests are generally preferred for web applications to provide a more responsive user experience and better utilize resources.
