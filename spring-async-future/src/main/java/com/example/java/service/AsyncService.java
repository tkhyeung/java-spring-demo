package com.example.java.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncService {



    @Async
    public CompletableFuture<Integer> asyncTest1(){
        log.info("Start test1");
        return CompletableFuture.completedFuture(100);
    }

    @Async
    public CompletableFuture<Integer> asyncTest2(){
        log.info("Start test2");
        return CompletableFuture.completedFuture(200);
    }

    @Async
    public CompletableFuture<Integer> asyncTest3(){
        log.info("Start test3");
        return CompletableFuture.completedFuture(300);
    }

    @Async
    public CompletableFuture<Integer> asyncTest4(){
        log.info("Start test4");
        return CompletableFuture.completedFuture(400);
    }

}
