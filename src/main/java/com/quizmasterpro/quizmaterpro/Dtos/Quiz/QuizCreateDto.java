package com.quizmasterpro.quizmaterpro.Dtos.Quiz;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class QuizCreateDto {
    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotNull(message = "Topic ID is required")
    private UUID topicId;

    @NotBlank(message = "Difficulty is required")
    @Size(max = 255, message = "Difficulty must be less than 255 characters")
    private String difficulty;

    @Positive(message = "Total questions must be positive")
    private int totalQuestions;

    @Positive(message = "Correct answers must be positive")
    private int correctAnswers;

    @NotNull(message = "Questions list is required")
    private List<String> questions;

    @NotNull(message = "Options list is required")
    private List<String> options;

    @NotNull(message = "Answers list is required")
    private List<String> answers;
}
