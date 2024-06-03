package com.quizmasterpro.quizmaterpro.Controllers;

import java.util.List;
import java.util.stream.Collectors;

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
import com.quizmasterpro.quizmaterpro.Dtos.Quiz.QuizSubmitDto;
import com.quizmasterpro.quizmaterpro.Models.Quiz;
import com.quizmasterpro.quizmaterpro.Services.QuizService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/quizzes/")
public class QuizController {

    @Autowired
    private QuizService quizService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("{id}")
    public ResponseEntity<?> getQuizById(@PathVariable String id) {
        try{
            Quiz quiz = quizService.getById(id);
            return ResponseEntity.ok(modelMapper.map(quiz, QuizDto.class));
        }catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<?> getQuizzesByUserId(@PathVariable String userId) {
        try{
            List<QuizDto> quizDtos = quizService.getByUserId(userId).stream().map(quiz->modelMapper.map(quiz, QuizDto.class)).collect(Collectors.toList());
            return ResponseEntity.ok(quizDtos);
        }catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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

    @PutMapping("{quizId}")
    public ResponseEntity<?> submitQuiz(@PathVariable String quizId, @RequestBody @Valid QuizSubmitDto quizSubmitDto, BindingResult bindingResult) {
        try{
            if(bindingResult.hasErrors()){
                return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
            }
            Quiz quiz = quizService.submit(quizId, quizSubmitDto);
            return ResponseEntity.ok(modelMapper.map(quiz, QuizDto.class));
        }catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable String id) {
        try{
            quizService.delete(id);
            return ResponseEntity.ok(true);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}