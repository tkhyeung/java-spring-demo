package com.example.java;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@Mock to create a mock
//@Spy is on real instance
//MockStatic can mock a static method
//ArgumentCaptor can capture the value passed onto the Mock
@Slf4j
@SpringBootApplication
public class JavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }

}
