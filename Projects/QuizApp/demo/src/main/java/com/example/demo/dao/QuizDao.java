package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
