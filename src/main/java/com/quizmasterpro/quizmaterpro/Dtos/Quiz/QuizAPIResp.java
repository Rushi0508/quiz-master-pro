package com.quizmasterpro.quizmaterpro.Dtos.Quiz;

import java.util.List;

import lombok.Data;

@Data
public class QuizAPIResp {
    private List<String> questions;
    private List<List<String>> options;
    private List<String> answers;
}
