package com.quizmasterpro.quizmaterpro.Dtos.User;

import java.util.List;
import java.util.UUID;

import com.quizmasterpro.quizmaterpro.Dtos.Quiz.QuizDto;

import lombok.Data;

@Data
public class UserDto {
    private UUID id;
    private String name;
    private String username;
    private String email;
    private List<QuizDto> quizzes;
}
