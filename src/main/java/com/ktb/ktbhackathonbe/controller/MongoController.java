package com.ktb.ktbhackathonbe.controller;

import com.ktb.ktbhackathonbe.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mongo")
public class MongoController {
    @Autowired
    MongoService mongoService;

    @ResponseBody
    @GetMapping("/test")
    public String mongoTest() {
        System.out.println("MongoController");
        return mongoService.mongoTest();
    }
}
