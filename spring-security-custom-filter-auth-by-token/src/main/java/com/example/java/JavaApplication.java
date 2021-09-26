package com.example.java;

import com.example.java.model.User;
import com.example.java.repository.UserRepository;
import com.example.java.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Demonstrate spring security with securing request with a token (custom filter)
 * Swagger with additional authentication header
 * Ref:
 * https://springdoc.org/
 * https://www.javadevjournal.com/spring/securing-a-restful-web-service-with-spring-security/
 */
@SpringBootApplication
@Slf4j
public class JavaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(User.builder().username("admin").build());
        log.info(userService.getUserByUsername("admin").toString());
    }
}
