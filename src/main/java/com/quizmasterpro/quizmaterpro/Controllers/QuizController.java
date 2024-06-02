package com.quizmasterpro.quizmaterpro.Controllers;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizmasterpro.quizmaterpro.Dtos.Quiz.QuizCreateDto;
import com.quizmasterpro.quizmaterpro.Dtos.Quiz.QuizDto;
import com.quizmasterpro.quizmaterpro.Models.Quiz;
import com.quizmasterpro.quizmaterpro.Services.QuizService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/quizzes/")
public class QuizController {

    @Autowired
    private QuizService quizService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable UUID id) {
        return quizService.getQuizById(id)
                .map(quiz -> new ResponseEntity<>(quiz, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<Quiz>> getQuizzesByUserId(@PathVariable UUID userId) {
        List<Quiz> quizzes = quizService.getQuizzesByUserId(userId);
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<?> createQuiz(@RequestBody @Valid QuizCreateDto quizCreateDto, BindingResult bindingResult) {
        try{
            if(bindingResult.hasErrors()){
                return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
            }
            Quiz quiz = quizService.create(quizCreateDto);
            return ResponseEntity.ok(modelMapper.map(quiz, QuizDto.class));
        }catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable UUID id) {
        quizService.deleteQuiz(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}