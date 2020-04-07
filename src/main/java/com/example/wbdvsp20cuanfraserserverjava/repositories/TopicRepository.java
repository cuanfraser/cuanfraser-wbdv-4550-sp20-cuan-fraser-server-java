package com.example.wbdvsp20cuanfraserserverjava.repositories;

import java.util.List;

import com.example.wbdvsp20cuanfraserserverjava.models.Topic;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TopicRepository extends CrudRepository<Topic, Integer> {

    @Query("SELECT topic FROM Topic topic")
    public List<Topic> findAllTopics();

    @Query("SELECT topic FROM Topic topic WHERE topic.id=:tid")
    public Topic findTopicById(@Param("tid") Integer tid);

    @Query("SELECT topic FROM Topic topic WHERE topic.lessonId=:lessonId")
    public List<Topic> findTopicsForLesson(@Param("lessonId") String lessonId);
}