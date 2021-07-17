package com.example.java;

import com.example.java.service.RestTemplateService;
import com.example.java.service.TestFeignService;
import com.example.java.service.WebClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@SpringBootApplication
@EnableFeignClients
public class JavaApplication implements CommandLineRunner {

    @Autowired
    private RestTemplateService restTemplateService;

    @Autowired
    private WebClientService webClientService;

    @Autowired
    private TestFeignService feignService;

    public static final String RESOURCE_URL = "https://jsonplaceholder.typicode.com";

    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Start http-access test");
//        restTemplateService.test();
//        webClientService.test();
        feignService.test();
    }
}
