package com.example.java;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Demo for spring websocket
// test by using postman
// connect to ws://localhost:8080/demo
// sending a text will return a random int
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
