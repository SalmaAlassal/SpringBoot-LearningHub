# HTTP Methods and Status Codes

This document provides a list of common HTTP methods and their meanings as well as a list of common HTTP status codes and their meanings.

# Table of Contents

1. [HTTP Methods](#http-methods)
2. [HTTP Status Codes](#http-status-codes)

# HTTP Methods

- [GET](#get)
- [PUT](#put)
- [UPDATE](#update)
- [DELETE](#delete)

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

## 3. Redirection Messages (300 - 399)

## 4. Client Error Responses (400 - 499)

- **400 Bad Request**: The server could not understand the request due to client error, and the request cannot be processed.
    - E.g. The request is missing a required parameter.

- **404 Not Found**: The server cannot find the requested resource.
    - E.g. The requested resource does not exist.

- **406 Not Acceptable**: The server cannot provide a response in a format acceptable to the client.
    - E.g. The client requests a xml response, but the server can only provide a json response.

## 5. Server Error Responses (500 - 599)

- **500 Internal Server Error**: It means that an error occurred on the server-side, and the server was unable to handle the request due to some internal issue.
    - E.g. Database connection error.
    - E.g. Division by zero.


------------------------------------------------------------