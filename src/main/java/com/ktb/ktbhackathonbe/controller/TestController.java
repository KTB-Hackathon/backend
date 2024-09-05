package com.ktb.ktbhackathonbe.controller;

import com.ktb.ktbhackathonbe.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/test")
@Controller
public class TestController {

    @Autowired
    TestService testService;

    @ResponseBody
    @GetMapping("")
    public String test() {
        return "This is Test";
    }

    @ResponseBody
    @PostMapping("/message")
    public String testMessage() {
        return testService.postTest();
    }
}
