package com.security.spring_security.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("auth")
//para poder validar el valor por defecto de los endpoints
@PreAuthorize("denyAll()")
public class TestAuthController {

    @GetMapping("/hello")
    @PreAuthorize("permitAll()")
    public String hello() {
        return "HelloWorld";
    }

    @GetMapping("/hello-secured")
    @PreAuthorize("hasAuthority('READ')")
    public String helloSecured() {
        return "HelloWorld Secured";
    }
}
