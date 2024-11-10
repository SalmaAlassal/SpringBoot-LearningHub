# Spring Boot Properties

> [Appendix A. Common application properties](https://docs.spring.io/spring-boot/docs/1.1.6.RELEASE/reference/html/common-application-properties.html)

Spring Boot properties are normally stored in a file named `application.properties`. This file is located in the `src/main/resources` folder of your project.

This file contains the different configuration which is required to run the application in a different environment, and each environment will have a different property defined by it. Inside the application properties file, we define every type of property like changing the port, database connectivity, and many more.

It's a key-value pair file that contains the configuration settings for the application.

## Port Configuration

The default port for a Spring Boot application is `8080`. You can change the port by adding the following property to the `application.properties` file:

```properties
server.port=8081
```
This will change the port to `8081` so the application will run on `http://localhost:8081`.

---------------------------------------------

## MVC View Properties

| Property | Description |
| -------- | ----------- |
|`spring.mvc.view.prefix=/location/of/views`|Specifies the prefix that gets prepended to view names when building a URL.|
|`spring.mvc.view.suffix=.html`|Specifies the suffix that gets appended to view names when rendering web pages.|

---------------------------------------------

## Database Properties

### H2 Database Properties

| Property | Description |
| -------- | ----------- |
|`spring.h2.console.enabled=true`|Specifies whether the H2 Console is enabled.|
|`spring.h2.console.path=/h2-console`|Specifies the path for the H2 Console.|

### Datasource Properties

| Property | Description |
| -------- | ----------- |
|`spring.datasource.url=jdbc:h2:mem:testdb`|Specifies the JDBC URL for the database.|
|`spring.datasource.username=sa`|Specifies the username for the database.|
|`spring.datasource.password=password`|Specifies the password for the database.|


- **JDBC URL**: The JDBC URL is a string that specifies the location of the database server and the name of the database to connect to. It is used by the JDBC driver to establish a connection to the database.
    
    `jdbc:h2:mem:testdb`
    - `jdbc:` indicates that the URL is for a JDBC (Java Database Connectivity) connection.
    - `h2:` specifies the database technology or driver being used. In this case, it is H2 Database.
    - `mem:` indicates that the H2 database will be created in memory rather than stored on disk.
    - `testdb` is the name of the database.

---------------------------------------------

## SQL Logging

| Property | Description |
| -------- | ----------- |
|`spring.jpa.show-sql=true` | Enables logging of SQL statements executed by JPA. This displays each SQL query in the application logs as they are executed, which helps with debugging and monitoring database interactions.|
|`spring.jpa.properties.hibernate.format_sql=true`|Formats the SQL output, making each statement more readable by organizing it with proper indentation and line breaks. |

---------------------------------------------
