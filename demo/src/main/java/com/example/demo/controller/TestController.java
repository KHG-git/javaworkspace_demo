package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TestOutputDTO;

@RestController
public class TestController {

    @GetMapping("/hello-world")
    public TestOutputDTO helloWorld() {
        return new TestOutputDTO("Hello, world");
    }
}
