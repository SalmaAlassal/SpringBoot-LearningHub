package com.example.demo.Repo;

import com.example.demo.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



//  By using the @RepositoryRestResource annotation, we are telling Spring to expose this repository as a RESTful API accessible at the path "/students".
//  This API will allow us to perform standard CRUD operations (Create, Read, Update, Delete) on student data, making it easy to interact with the application's data
//  using HTTP requests. For example, we can use HTTP GET requests to retrieve a list of all students, HTTP POST requests to create new students, and so on.


// collectionResourceRel is used to customize the collection resource name.

// path is used to customize the base path for accessing the API. The full URL path for the collection of students will be "/students".
// This means that when you make HTTP requests to "/students", you will be interacting with the student data.


// Try it out
// Get Request : http://localhost:8080/students

// Post Request : http://localhost:8080/students
// Body :
// {
//     "sid": 100,
//     "sname": "Bingo",
//     "stech": "Java"
// }

// Put Request : http://localhost:8080/students/1
// Body :
// {
//     "sid": 1,
//     "sname": "xyz",
//     "stech": "ML"
// }

// Delete Request : http://localhost:8080/students/1




@RepositoryRestResource(collectionResourceRel = "students", path = "students")
public interface StudentRepo extends JpaRepository<Student, Integer> {
}