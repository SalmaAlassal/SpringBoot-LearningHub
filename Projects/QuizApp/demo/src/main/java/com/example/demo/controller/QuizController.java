package com.example.demo.controller;

import com.example.demo.model.QuestionWrapper;
import com.example.demo.model.Response;
import com.example.demo.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    // URL : http://localhost:8080/quiz/create?title=Test&category=Math&numberOfQuestions=2
    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String title, @RequestParam String category, @RequestParam int numberOfQuestions) {
        return quizService.createQuiz(title, category, numberOfQuestions);
    }

    // URL : http://localhost:8080/quiz/get/1
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) {
        return quizService.calculateScore(id, responses);
    }
}