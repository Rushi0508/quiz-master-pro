package com.quizmasterpro.quizmaterpro.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.quizmasterpro.quizmaterpro.Models.Topic;

@Repository
public interface TopicRepository extends MongoRepository<Topic, String> {
    Optional<Topic> findByName(String name);
    Optional<Topic> findByNameIgnoreCase(String name);
}
