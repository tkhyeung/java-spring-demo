package com.example.java.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FooService {
    public void get(){
      log.info("get");
    }
}
