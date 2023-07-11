package com.cineclub.cineclubback.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/")
public class TestController {
    
    @GetMapping
    public String test() {
        return "Hellow World";
    }
    
}
