package com.example.wbdvsp20cuanfraserserverjava.services;

import com.example.wbdvsp20cuanfraserserverjava.models.Topic;
import com.example.wbdvsp20cuanfraserserverjava.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    public Topic createTopic(String lid, Topic newTopic) {
        return topicRepository.save(newTopic);
    }

    public List<Topic> findTopicsForLesson(String lid) {
        return topicRepository.findTopicsForLesson(lid);
    }

    public int updateTopic(Integer tid, Topic updatedTopic) {
        topicRepository.deleteById(tid);
        topicRepository.save(updatedTopic);
        return 0;
    }

    public int deleteTopic(Integer tid) {
        topicRepository.deleteById(tid);
        return 1;
    }

    public List<Topic> findAllTopics() {
        return topicRepository.findAllTopics();
    }

    public Topic findTopicById(Integer tid) {
        return topicRepository.findTopicById(tid);
    }
}