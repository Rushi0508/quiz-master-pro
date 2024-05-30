package com.quizmasterpro.quizmasterpro.Models;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
    
    private String difficulty;
    private int totalQuestions;
    private int correctAnswers;

    private boolean completed = false;
    private int currentQuestionIndex;

     @ElementCollection
    private List<String> questions;
    
    @ElementCollection
    private List<String> options;
    
    @ElementCollection
    private List<String> answers;
    
    @ElementCollection
    private List<String> userResponses;
    
    private Date dateTaken = new Date();
}
