package com.quizmasterpro.quizmaterpro.Dtos.Quiz;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuizSubmitDto {
    @NotNull(message = "Quiz id is required")
    private UUID quizId;

    private boolean isCompleted = true;

    @NotNull(message = "User responses is required")
    private List<String> userResponses;
    
}