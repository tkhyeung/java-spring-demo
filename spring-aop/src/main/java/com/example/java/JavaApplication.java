package com.example.java;

import com.example.java.service.ExampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Demonstrate usage of AOP for logging for example.
 */
@SpringBootApplication
@Slf4j
public class JavaApplication implements CommandLineRunner {

    @Autowired
    private ExampleService exampleService;

    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        exampleService.printMessages();
        exampleService.printMessages("A");
        exampleService.printMessages("A", "B");
        exampleService.printMessages("A", "B", "C");
        exampleService.printMessage2("D", "E");
        exampleService.errorMethod();
    }
}
