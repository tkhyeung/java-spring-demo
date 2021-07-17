package com.example.java;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BeanImpl2 implements Bean {
    private String value = "Bean2!";

    @Override
    public void printValue() {
        log.info("BeanImpl2 - value: {}", value);

    }
}
