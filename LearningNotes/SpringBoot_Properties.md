# Spring Boot Properties

Spring Boot properties are normally stored in a file named `application.properties`. This file is located in the `src/main/resources` folder of your project.

## Some Common Properties

Here are some properties that are commonly used in Spring Boot applications:

| Property | Description |
| -------- | ----------- |
|`spring.mvc.view.prefix=/location/of/views`|Specifies the prefix that gets prepended to view names when building a URL.|
|`spring.mvc.view.suffix=.html`|Specifies the suffix that gets appended to view names when rendering web pages.|


--------------------

## Database Properties (Including H2 Database)

| Property | Description |
| -------- | ----------- |
|`spring.h2.console.enabled=true`|Specifies whether the H2 Console is enabled.|
|`spring.h2.console.path=/h2-console`|Specifies the path for the H2 Console.|
|`spring.datasource.url=jdbc:h2:mem:testdb`|Specifies the JDBC URL for the H2 database.|
|`spring.datasource.username=sa`|Specifies the username for the H2 database.|
|`spring.datasource.password=`|Specifies the password for the H2 database.|
|`spring.jpa.defer-datasource-initialization=true`|Delay the datasource initialization until all the beans are created.|


> JDBC is a Java API for connecting to relational databases. It provides a standard way of accessing databases using SQL statements. JDBC is part of the Java Standard Edition (SE) platform and is included in all Java SE distributions.

> `jdbc:h2:mem:testdb` :

> `jdbc:` indicates that the URL is for a JDBC (Java Database Connectivity) connection.

> `h2:` specifies the database technology or driver being used. In this case, it is H2 Database.

> `mem:` indicates that the H2 database will be created in memory rather than stored on disk.

> `testdb` is the name of the database.