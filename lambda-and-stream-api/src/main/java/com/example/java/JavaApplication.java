package com.example.java;

import com.example.java.exercise.StreamService;
import com.example.java.exercise.LambdaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class JavaApplication implements CommandLineRunner {

    @Autowired
    private StreamService service;

    @Autowired
    private LambdaService lambdaService;

    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        lambdaService.test();
        service.test();


        System.exit(0);
    }
}