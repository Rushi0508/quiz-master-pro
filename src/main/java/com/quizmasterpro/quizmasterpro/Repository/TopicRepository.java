package com.quizmasterpro.quizmasterpro.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizmasterpro.quizmasterpro.Models.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID> {
    
}
