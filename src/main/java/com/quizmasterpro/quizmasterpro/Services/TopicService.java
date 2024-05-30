package com.quizmasterpro.quizmasterpro.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizmasterpro.quizmasterpro.Models.Topic;
import com.quizmasterpro.quizmasterpro.Repository.TopicRepository;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> getAllTopics(){
        return topicRepository.findAll();
    }

    public Optional<Topic> getTopicById(UUID id) {
        return topicRepository.findById(id);
    }

    public Topic saveTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public void deleteTopic(UUID id) {
        topicRepository.deleteById(id);
    }
}
