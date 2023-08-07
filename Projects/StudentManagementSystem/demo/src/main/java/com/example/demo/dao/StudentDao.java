package com.example.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Student;

// We don't need to annotate with @Repository because JpaRepository is annotated with @Repository internally.
@Repository
public interface StudentDao extends JpaRepository<Student, Long> {
}
