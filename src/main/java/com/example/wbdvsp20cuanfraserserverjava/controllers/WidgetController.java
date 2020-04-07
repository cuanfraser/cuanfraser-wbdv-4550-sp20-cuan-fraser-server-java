package com.example.wbdvsp20cuanfraserserverjava.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.wbdvsp20cuanfraserserverjava.models.Widget;
import com.example.wbdvsp20cuanfraserserverjava.services.WidgetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class WidgetController {

    @Autowired
    WidgetService service;

    @PostMapping("/api/topics/{tid}/widgets")
    public Widget createWidget(@PathVariable("tid") String tid, @RequestBody Widget newWidget) {
        try {
            Integer tidInt = Integer.valueOf(tid);
            return service.createWidget(tidInt, newWidget);
        } catch (NumberFormatException e) {
            return new Widget();
        }
    }

    @GetMapping("/api/topics/{tid}/widgets")
    public List<Widget> findWidgetsForTopic(@PathVariable("tid") String tid) {
        try {
            Integer tidInt = Integer.valueOf(tid);
            return service.findWidgetsForTopic(tidInt);
        } catch (NumberFormatException e) {
            return new ArrayList<Widget>();
        }
    }

    @PutMapping("/api/widgets/{wid}")
    public int updateWidget(@PathVariable("wid") String wid, @RequestBody Widget updatedWidget) {
        try {
            Integer widInt = Integer.valueOf(wid);
            return service.updateWidget(widInt, updatedWidget);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @GetMapping("/api/widgets/{wid}")
    public Widget findWidgetById(@PathVariable("wid") String wid) {
        try {
            Integer widInt = Integer.valueOf(wid);
            return service.findWidgetById(widInt);
        } catch (NumberFormatException e) {
            return new Widget();
        }
    }

    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets() {
        return service.findAllWidgets();
    }

    @DeleteMapping("/api/widgets/{wid}")
    public int deleteWidget(@PathVariable("wid") String wid) {
        try {
            System.out.println("lads lads");
            Integer widInt = Integer.valueOf(wid);
            return service.deleteWidget(widInt);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}