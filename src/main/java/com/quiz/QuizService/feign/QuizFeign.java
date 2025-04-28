package com.quiz.QuizService.feign;

import com.quiz.QuizService.model.QuestionWrapper;
import com.quiz.QuizService.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTIONSERVICE")
public interface QuizFeign {
    @GetMapping("question/generate-question")
    public ResponseEntity<List<Integer>> generateQuestion(@RequestParam String category, @RequestParam int numberOfQuestions);

    @PostMapping("question/get-questions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionIds);

    @PostMapping("question/get-score")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> response);
}
