package com.learn.spring.UnitTest.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
public class HelloWorldController {

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello world!!";
    }
    
}
