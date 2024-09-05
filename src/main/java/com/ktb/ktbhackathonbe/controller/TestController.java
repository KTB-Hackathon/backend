package com.ktb.ktbhackathonbe.controller;

import com.ktb.ktbhackathonbe.dto.RequestMessageDto;
import com.ktb.ktbhackathonbe.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String testMessage(@RequestBody RequestMessageDto requestMessageDto) {
        return testService.postTest(requestMessageDto);
    }
}
