package com.quizmasterpro.quizmaterpro.Dtos.Quiz;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class QuizCreateDto {
    @NotNull(message = "User ID is required")
    private String userId;
    
    @NotNull(message = "User ID is required")
    private String topicId;

    @NotBlank(message = "Difficulty is required")
    @Size(max = 255, message = "Difficulty must be less than 255 characters")
    private String difficulty;

    @Positive(message = "Total questions must be positive")
    private int totalQuestions;
}
