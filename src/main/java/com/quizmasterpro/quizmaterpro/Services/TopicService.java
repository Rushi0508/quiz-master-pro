package com.quizmasterpro.quizmaterpro.Services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizmasterpro.quizmaterpro.Dtos.Topic.TopicCreateDto;
import com.quizmasterpro.quizmaterpro.Models.Topic;
import com.quizmasterpro.quizmaterpro.Repository.TopicRepository;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Topic> getAllTopics(){
        return topicRepository.findAll();
    }

    public Optional<Topic> getTopicById(String id) {
        return topicRepository.findById(id);
    }

    public Topic saveTopic(TopicCreateDto topicCreateDto) {
        if(topicRepository.findByName(topicCreateDto.getName()).isPresent()){
            throw new IllegalArgumentException("Topic already exists");
        }
        return topicRepository.save(modelMapper.map(topicCreateDto, Topic.class));
    }

    public void deleteTopicById(String id) {
        if (topicRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("No topic found to delete");
        }
        topicRepository.deleteById(id);
    }
}
