package com.example.java;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BeanImpl1 implements Bean {

    private String value = "Bean1!";
    @Override
    public void printValue() {
        log.info("BeanImpl1 - value: {}", value);

    }
}
