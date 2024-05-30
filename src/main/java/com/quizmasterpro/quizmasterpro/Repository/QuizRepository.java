package com.quizmasterpro.quizmasterpro.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizmasterpro.quizmasterpro.Models.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, UUID> {
    List<Quiz> findByUserId(UUID id);
}
