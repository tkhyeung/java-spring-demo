package com.example.java;

import com.example.java.service.RestTemplateService;
import com.example.java.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaApplication implements CommandLineRunner {

    @Autowired
    private RestTemplateService restTemplateService;

    @Autowired
    private WebClientService webClientService;

    public static final String RESOURCE_URL = "https://jsonplaceholder.typicode.com";

    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        restTemplateService.test();
        webClientService.test();
    }
}
