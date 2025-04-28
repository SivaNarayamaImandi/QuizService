package com.quiz.QuizService.service;

import com.quiz.QuizService.feign.QuizFeign;
import com.quiz.QuizService.model.QuestionWrapper;
import com.quiz.QuizService.model.Quiz;
import com.quiz.QuizService.model.Response;
import com.quiz.QuizService.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizRepo quizRepo;

    @Autowired
    QuizFeign quizFeign;

    public ResponseEntity<String> createQuiz(String category, int numberOfQuestions, String quizTitle) {
        List<Integer> questionIds = quizFeign.generateQuestion(category, numberOfQuestions).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(quizTitle);
        quiz.setQuestions(questionIds);
        if (quizRepo.save(quiz) != null) {
            try {
                return new ResponseEntity<String>("Questions Added Successful.....", HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<String>("Questions Added Failed.....", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
        if (!quizFeign.getQuestions(quizRepo.findById(id).get().getQuestions()).getBody().isEmpty()) {
            try {
                return quizFeign.getQuestions(quizRepo.findById(id).get().getQuestions());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return quizFeign.getQuestions(quizRepo.findById(id).get().getQuestions());
    }

    public ResponseEntity<Integer> submitQuiz(Integer id, List<Response> response) {
        if(quizFeign.getScore(response).getBody() > 0)
            return quizFeign.getScore(response);
        return quizFeign.getScore(response);
    }
}
