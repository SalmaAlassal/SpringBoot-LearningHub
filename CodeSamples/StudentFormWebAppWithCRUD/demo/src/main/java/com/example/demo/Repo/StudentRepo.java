package com.example.demo.Repo;

import com.example.demo.Model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


// This interface extends CrudRepository, a Spring Data interface with CRUD methods for the entity class that is being managed.
// The CrudRepository provides CRUD operations such as save(), findAll(), findById(), deleteById(), etc.
// CRUD stands for Create, Read, Update, and Delete.

// The CrudRepository interface takes the entity class and the primary key as type arguments.
public interface StudentRepo extends CrudRepository<Student, Integer> {
    // The method name should start with findBy, followed by the variable name to be searched.
    // It is mandatory to follow this naming convention or else Spring Data will not be able to create the query.
    List<Student> findByStech(String tech);

    // You can use the following keywords to create the query: And, Or, Between, LessThan, GreaterThan, IsNull, NotNull, Like, NotLike, etc.
    List<Student> findBySidGreaterThan(int sid);


    // The following method is used to sort the data by name.
    @Query("from Student where stech= ?1 order by sname") // This is a JPQL query.
    List<Student> findByStechSorted(String tech);
}