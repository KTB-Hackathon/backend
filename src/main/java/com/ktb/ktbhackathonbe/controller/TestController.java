package com.ktb.ktbhackathonbe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/test")
@Controller
public class TestController {

    @ResponseBody
    @GetMapping("")
    public String test() {
        return "This is Test";
    }
}
