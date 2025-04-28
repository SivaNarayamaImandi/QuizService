package com.quiz.QuizService.model;

import lombok.Data;

@Data
public class QuizDto {
    private String category;
    private int numberOfQuestions;
    private String quizTitle;
}
