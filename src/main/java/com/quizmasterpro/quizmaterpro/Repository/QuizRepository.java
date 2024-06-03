package com.quizmasterpro.quizmaterpro.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.quizmasterpro.quizmaterpro.Models.Quiz;

@Repository
public interface QuizRepository extends MongoRepository<Quiz, String> {
    List<Quiz> findByUserId(String id);
}
