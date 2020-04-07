package com.example.wbdvsp20cuanfraserserverjava.services;

import java.util.List;

import com.example.wbdvsp20cuanfraserserverjava.models.Topic;
import com.example.wbdvsp20cuanfraserserverjava.models.Widget;
import com.example.wbdvsp20cuanfraserserverjava.repositories.TopicRepository;
import com.example.wbdvsp20cuanfraserserverjava.repositories.WidgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WidgetService {

    @Autowired
    WidgetRepository widgetRepository;

    @Autowired
    TopicRepository topicRepository;

    public Widget createWidget(Integer tid, Widget newWidget) {
        Topic topic = topicRepository.findTopicById(tid);
        newWidget.setTopic(topic);
        return widgetRepository.save(newWidget);
    }

    public List<Widget> findWidgetsForTopic(Integer tid) {
        return widgetRepository.findWidgetsForTopic(tid);
    }

    public int updateWidget(Integer wid, Widget updatedWidget) {
        widgetRepository.deleteById(wid);
        widgetRepository.save(updatedWidget);
        return 0;
    }

    public int deleteWidget(Integer wid) {
        System.out.println("deleting my dudes");
        widgetRepository.deleteById(wid);
        return 1;
    }

    public List<Widget> findAllWidgets() {
        return widgetRepository.findAllWidgets();
    }

    public Widget findWidgetById(Integer wid) {
        return widgetRepository.findWidgetById(wid);
    }
}