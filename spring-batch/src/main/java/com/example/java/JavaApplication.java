package com.example.java;

import com.example.java.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class JavaApplication implements CommandLineRunner {

    @Autowired
    StudentRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("ROOT path is {}", this.getClass().getResource("/").getPath());
        log.info("batch run");
        log.info("Java Application, find all Student::{}", repository.findAll());
    }
}