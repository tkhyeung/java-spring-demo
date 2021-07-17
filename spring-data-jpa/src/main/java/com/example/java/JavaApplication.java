package com.example.java;

import com.example.java.model.Person;
import com.example.java.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootApplication
public class JavaApplication implements CommandLineRunner {

    @Autowired
    //private PersonRepository repository;
    private PersonService service;

    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Person> personList = Arrays.asList(
            Person.builder().name("A").email("a@a.com").age("10").contactNumber("12345678").status("0").build(),
            Person.builder().name("B").age("20").contactNumber("23456789").status("1").build(),
            Person.builder().name("C").email("c@c.com").age("30").contactNumber("34567890").status("1").build()
        );
        service.saveAll(personList);
        log.info("saved all");

        log.info("findAll");
        service.findAll(Sort.by(Sort.Direction.DESC, "id")).forEach(s -> log.info(s.toString()));

        log.info("findByName");
        service.findByName("A").forEach(s -> log.info(s.toString()));

        log.info("findByCustomQuery1");
        service.findByCustomQuery1().forEach(s -> log.info(s.toString()));

        log.info("findByCustomQuery2");
        service.findByCustomQuery2().forEach(s -> log.info(s.toString()));

        log.info("There are total of {} persons", service.count());

    }
}