package com.quizmasterpro.quizmaterpro.Models;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Data;

@Data
@Document(collection = "quizzes")
public class Quiz {
    @Id
    private String id;

    @DBRef
    private User user;

    @DBRef
    private Topic topic;
    
    private String difficulty;
    private int totalQuestions;
    private int correctAnswers;

    private boolean completed = false;
    private int currentQuestionIndex;

    private List<String> questions;
    
    private List<List<String>> options;
    
    private List<String> answers;
    
    private List<String> userResponses;
    
    private Date dateTaken = new Date();
}
