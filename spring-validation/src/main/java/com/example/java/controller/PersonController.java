package com.example.java.controller;

import com.example.java.config.ValidateAdult;
import com.example.java.config.ValidateChildren;
import com.example.java.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "persons")
public class PersonController {


    @PostMapping("/adult")
    @ResponseStatus(HttpStatus.CREATED)
    public Person createAdult(
//            @Valid
            @Validated(ValidateAdult.class)
            @RequestBody
                    Person person) {
        log.info("Received Request: create Person {}", person);
        return person;
    }


    @PostMapping("/children")
    @ResponseStatus(HttpStatus.CREATED)
    public Person createChildren(
//            @Valid
            @Validated(ValidateChildren.class)
            @RequestBody
                    Person person) {
        log.info("Received Request: create Person {}", person);
        return person;
    }

}
