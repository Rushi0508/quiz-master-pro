package com.quizmasterpro.quizmaterpro.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizmasterpro.quizmaterpro.Models.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, UUID> {
    
}
