package com.quizmasterpro.quizmaterpro.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.quizmasterpro.quizmaterpro.Dtos.Quiz.QuizSubmitDto;
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

    public Quiz getById(String id) {
        return quizRepository.findById(id).orElseThrow(()->new IllegalArgumentException("No quiz found"));
    }

    public List<Quiz> getByUserId(String userId) {
        return quizRepository.findByUserId(userId);
    }

    public Quiz create(QuizCreateDto quizCreateDto) {
        User user = userRepository.findById(quizCreateDto.getUserId()).orElseThrow(()->new UsernameNotFoundException("User not found"));
        if(quizRepository.findByUserId(quizCreateDto.getUserId()).size()>=5){
            throw new IllegalArgumentException("You can only give 5 quizzes in free plan");
        }
        Topic topic =topicRepository.findById(quizCreateDto.getTopicId()).orElseThrow(()->new IllegalArgumentException("Topic not found"));
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        QuizAPIReq quizAPIReq = new QuizAPIReq();
        quizAPIReq.setTopic(topic.getName());
        quizAPIReq.setNoOfQuestions(quizCreateDto.getTotalQuestions());
        quizAPIReq.setDifficulty(quizCreateDto.getDifficulty());
        HttpEntity<QuizAPIReq> request = new HttpEntity<>(quizAPIReq, headers);
        ResponseEntity<QuizAPIResp> response = restTemplate.postForEntity("https://quiz-master-pro-gemini-layer.vercel.app/gemini", request, QuizAPIResp.class);
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

    public Quiz submit(String quizId, QuizSubmitDto quizSubmitDto){
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(()->new IllegalArgumentException("No quiz found"));
        userRepository.findById(quizSubmitDto.getUserId()).orElseThrow(()->new UsernameNotFoundException("User not found"));
        if(quiz.isCompleted()){
            throw new IllegalArgumentException("You already have completed the quiz");
        }
        quiz.setUserResponses(quizSubmitDto.getUserResponses());
        List<String> answers = quiz.getAnswers();
        List<String> userResponses = quizSubmitDto.getUserResponses();
        int correctAnswers = 0;
        for(int i=0;i<answers.size();i++){
            if(answers.get(i).equals(userResponses.get(i))){
                correctAnswers++;
            }
        }
        quiz.setCorrectAnswers(correctAnswers);
        quiz.setCompleted(true);
        quizRepository.save(quiz);
        return quiz;
    }

    public void delete(String id) {
        if (quizRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("No quiz found to delete");
        }
        quizRepository.deleteById(id);
    }
}
