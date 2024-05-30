package com.quizmasterpro.quizmasterpro.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizmasterpro.quizmasterpro.Models.Quiz;
import com.quizmasterpro.quizmasterpro.Services.QuizService;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable UUID id) {
        return quizService.getQuizById(id)
                .map(quiz -> new ResponseEntity<>(quiz, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Quiz>> getQuizzesByUserId(@PathVariable UUID userId) {
        List<Quiz> quizzes = quizService.getQuizzesByUserId(userId);
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        Quiz savedQuiz = quizService.saveQuiz(quiz);
        return new ResponseEntity<>(savedQuiz, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable UUID id) {
        quizService.deleteQuiz(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}