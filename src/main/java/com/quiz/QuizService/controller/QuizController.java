package com.quiz.QuizService.controller;

import com.quiz.QuizService.model.QuestionWrapper;
import com.quiz.QuizService.model.Response;
import com.quiz.QuizService.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    //    http://localhost:8080/quiz/create?category=java&numberOfQuestions=2&quizTitle=JQuiz
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numberOfQuestions, @RequestParam String quizTitle) {
        return quizService.createQuiz(category, numberOfQuestions, quizTitle);
    }

    @GetMapping("get-quiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id) {
        return quizService.getQuiz(id);
    }

    @PostMapping("submit-quiz/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response) {
        return quizService.submitQuiz(id, response);
    }
}
