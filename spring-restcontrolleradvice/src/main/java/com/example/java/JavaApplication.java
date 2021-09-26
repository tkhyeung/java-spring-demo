package com.example.java;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Demonstrate usage of @RestControllerAdvice for global exception handling and custom exceptions
 */
@SpringBootApplication
@Slf4j
public class JavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }
}
