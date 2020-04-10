package com.example.wbdvsp20cuanfraserserverjava.controllers;

import com.example.wbdvsp20cuanfraserserverjava.models.Topic;
import com.example.wbdvsp20cuanfraserserverjava.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TopicController {
    @Autowired
    TopicService service;

    @PostMapping("/api/lessons/{lid}/topics")
    public Topic createTopic(@PathVariable("lid") String lid, @RequestBody Topic newTopic) {
        return service.createTopic(lid, newTopic);
    }

    @GetMapping("/api/lessons/{lid}/topics")
    public List<Topic> findTopicsForLesson(@PathVariable("lid") String lid) {
        return service.findTopicsForLesson(lid);
    }

    @PutMapping("/api/topics/{tid}")
    public int updateTopic(@PathVariable("tid") String tid, @RequestBody Topic updatedTopic) {
        try {
            Integer tidInt = Integer.valueOf(tid);
            return service.updateTopic(tidInt, updatedTopic);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @DeleteMapping("/api/topics/{tid}")
    public int deleteTopic(@PathVariable("tid") String tid) {
        try {
            Integer tidInt = Integer.valueOf(tid);
            return service.deleteTopic(tidInt);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @GetMapping("/api/topics")
    public List<Topic> findAllTopics() {
        return service.findAllTopics();
    }

    @GetMapping("/api/topics/{tid}")
    public Topic findTopicById(@PathVariable("tid") String tid) {
        try {
            Integer tidInt = Integer.valueOf(tid);
            return service.findTopicById(tidInt);
        } catch (NumberFormatException e) {
            return new Topic();
        }
    }

}