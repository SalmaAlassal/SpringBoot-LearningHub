package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

// @Data is comes from lombok, it is used to generate getter and setter methods
@Data
@Entity
public class Question {
    @Id
    // @GeneratedValue is used to generate a value for the field automatically
    // strategy = GenerationType.SEQUENCE is used to generate a value for the field automatically using a sequence of numbers  (1, 2, 3, ...)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctOption;
    private String difficultyLevel;
    private String category;
}
