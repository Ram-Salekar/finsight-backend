package com.finsight.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Simple sanity-check endpoint — confirms the app is up and routing works
 * before any real domain endpoints exist.
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("welcome")
    public String welcome() {
        return new String("Welcome to Finsight");
    }

}
