package com.example.java;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Demo for spring validation
// Can be used on JPA entity, Path Variables and Request Parameters, Request Body
// @Valid @RequestBody
// Custom Validation Annotation
// Validation Group
@SpringBootApplication
@Slf4j
public class JavaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
