package com.quizmasterpro.quizmaterpro.Dtos.Quiz;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuizSubmitDto {
    @NotNull(message = "User id is required")
    private String userId;

    private boolean isCompleted = true;

    @NotNull(message = "User responses is required")
    private List<String> userResponses;
    
}