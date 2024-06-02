package com.quizmasterpro.quizmaterpro.Dtos.Quiz;

import lombok.Data;

@Data
public class QuizAPIReq {
    private String topic;
    private String difficulty;
    private int noOfQuestions;
}
