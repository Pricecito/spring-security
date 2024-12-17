package com.security.spring_security.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("auth")
public class TestAuthController {

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @GetMapping("/hello-secured")
    public String helloSecured() {
        return "HelloWorld Secured";
    }
}
