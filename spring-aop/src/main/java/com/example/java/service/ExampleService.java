package com.example.java.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExampleService {

    public void printMessages(String... message){
        log.info("Called ExampleService");
            for(String m: message){
                log.info("ExampleService: message: {}", m);
            }
    }

    public void printMessage2(String msg1, String msg2){
        log.info("printMessage2: msg1: {}, msg2: {}", msg1, msg2);
    }

    public void errorMethod(){
        throw new RuntimeException("ERROR");
    }
}
