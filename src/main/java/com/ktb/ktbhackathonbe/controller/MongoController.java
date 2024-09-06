package com.ktb.ktbhackathonbe.controller;

import com.ktb.ktbhackathonbe.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mongo")
public class MongoController {
    @Autowired
    MongoService mongoService;

    @GetMapping("/test")
    public String mongoTest() {
        return mongoService.mongoTest();
    }
}
