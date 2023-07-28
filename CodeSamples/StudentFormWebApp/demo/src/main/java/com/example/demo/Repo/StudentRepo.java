package com.example.demo.Repo;

import com.example.demo.Model.Student;
import org.springframework.data.repository.CrudRepository;


// This interface extends CrudRepository, a Spring Data interface with CRUD methods for the entity class that is being managed.
// The CrudRepository provides CRUD operations such as save(), findAll(), findById(), deleteById(), etc.
// CRUD stands for Create, Read, Update, and Delete.

// The CrudRepository interface takes the entity class and the primary key as type arguments.
public interface StudentRepo extends CrudRepository<Student, Integer>{
}