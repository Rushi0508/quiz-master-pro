package com.quizmasterpro.quizmasterpro.Dtos.Quiz;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class QuizDto {
    private UUID id;
    private UUID userId;
    private UUID topicId;
    private String difficulty;
    private int totalQuestions;
    private int correctAnswers;
    private boolean completed;
    private int currentQuestionIndex;
    private List<String> questions;
    private List<String> options;
    private List<String> answers;
    private List<String> userResponses;
    private Date dateTaken;
}
