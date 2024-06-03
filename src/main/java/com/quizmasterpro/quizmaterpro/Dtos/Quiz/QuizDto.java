package com.quizmasterpro.quizmaterpro.Dtos.Quiz;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class QuizDto {
    private String id;
    private String userId;
    private String topicId;
    private String difficulty;
    private int totalQuestions;
    private int correctAnswers;
    private boolean completed;
    private int currentQuestionIndex;
    private List<String> questions;
    private List<List<String>> options;
    private List<String> userResponses;
    private Date dateTaken;
}
