package com.example.java.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecureController {

    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @GetMapping("/test")
    public String test() {
        return "Hello world!";
    }
}
