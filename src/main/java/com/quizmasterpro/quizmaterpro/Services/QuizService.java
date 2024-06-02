package com.quizmasterpro.quizmaterpro.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.quizmasterpro.quizmaterpro.Dtos.Quiz.QuizAPIReq;
import com.quizmasterpro.quizmaterpro.Dtos.Quiz.QuizAPIResp;
import com.quizmasterpro.quizmaterpro.Dtos.Quiz.QuizCreateDto;
import com.quizmasterpro.quizmaterpro.Models.Quiz;
import com.quizmasterpro.quizmaterpro.Models.Topic;
import com.quizmasterpro.quizmaterpro.Models.User;
import com.quizmasterpro.quizmaterpro.Repository.QuizRepository;
import com.quizmasterpro.quizmaterpro.Repository.TopicRepository;
import com.quizmasterpro.quizmaterpro.Repository.UserRepository;

@Service
public class QuizService {
     @Autowired
    private QuizRepository quizRepository;
     @Autowired
    private UserRepository userRepository;
     @Autowired
    private TopicRepository topicRepository;

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Optional<Quiz> getQuizById(UUID id) {
        return quizRepository.findById(id);
    }

    public List<Quiz> getQuizzesByUserId(UUID userId) {
        return quizRepository.findByUserId(userId);
    }

    public Quiz create(QuizCreateDto quizCreateDto) {
        User user = userRepository.findById(quizCreateDto.getUserId()).orElseThrow(()->new UsernameNotFoundException("User not found"));
        Topic topic =topicRepository.findById(quizCreateDto.getTopicId()).orElseThrow(()->new IllegalArgumentException("Topic not found"));
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        QuizAPIReq quizAPIReq = new QuizAPIReq();
        quizAPIReq.setTopic(topic.getName());
        quizAPIReq.setNoOfQuestions(quizCreateDto.getTotalQuestions());
        quizAPIReq.setDifficulty(quizCreateDto.getDifficulty());
        HttpEntity<QuizAPIReq> request = new HttpEntity<>(quizAPIReq, headers);
        ResponseEntity<QuizAPIResp> response = restTemplate.postForEntity("http://localhost:3000/gemini", request, QuizAPIResp.class);
        QuizAPIResp resp = response.getBody();

        Quiz quiz = new Quiz();
        quiz.setUser(user);
        quiz.setTopic(topic);
        quiz.setCompleted(false);
        quiz.setDifficulty(quizCreateDto.getDifficulty());
        quiz.setTotalQuestions(quizCreateDto.getTotalQuestions());
        quiz.setQuestions(resp.getQuestions());
        quiz.setOptions(resp.getOptions());
        quiz.setAnswers(resp.getAnswers());
        quizRepository.save(quiz);
        return quiz;
    }

    public void deleteQuiz(UUID id) {
        quizRepository.deleteById(id);
    }
}
