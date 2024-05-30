package com.quizmasterpro.quizmaterpro.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizmasterpro.quizmaterpro.Models.Quiz;
import com.quizmasterpro.quizmaterpro.Repository.QuizRepository;

@Service
public class QuizService {
     @Autowired
    private QuizRepository quizRepository;

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Optional<Quiz> getQuizById(UUID id) {
        return quizRepository.findById(id);
    }

    public List<Quiz> getQuizzesByUserId(UUID userId) {
        return quizRepository.findByUserId(userId);
    }

    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public void deleteQuiz(UUID id) {
        quizRepository.deleteById(id);
    }
}
