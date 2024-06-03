package com.quizmasterpro.quizmaterpro.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizmasterpro.quizmaterpro.Dtos.Topic.TopicCreateDto;
import com.quizmasterpro.quizmaterpro.Dtos.Topic.TopicDto;
import com.quizmasterpro.quizmaterpro.Models.Topic;
import com.quizmasterpro.quizmaterpro.Services.TopicService;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity<List<TopicDto>> getAllTopics() {
        List<TopicDto> topicDtos = topicService.getAllTopics().stream().map(topic->modelMapper.map(topic, TopicDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(topicDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTopicById(@PathVariable String id) {
        var topic =  topicService.getTopicById(id);
        if(topic.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(modelMapper.map(topic, TopicDto.class));
    }

    @PostMapping
    public ResponseEntity<?> createTopic(@RequestBody TopicCreateDto topicCreateDto) {
        try{
            Topic savedTopic = topicService.saveTopic(topicCreateDto);
            return ResponseEntity.ok(modelMapper.map(savedTopic, TopicDto.class));
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable String id) {
        try{
            topicService.deleteTopicById(id);
            return ResponseEntity.ok(true);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
