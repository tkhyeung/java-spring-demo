package com.example.java.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

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

    public volatile int counter = 0;

    @Async
    public synchronized CompletableFuture<Void> asyncIncrementWithSynchronized(int id) {
        return CompletableFuture.runAsync(() -> {
            log.info("before Increment::id:{}::counter::{}",id,counter);
            int localCounter = counter;
            localCounter++;
            counter = localCounter;
            log.info("after Increment::id:{}::counter::{}",id,counter);
        });
    }

    public final AtomicInteger atomicCounter = new AtomicInteger(0);

    public int getValue() {
        return atomicCounter.get();
    }
    @Async
    public CompletableFuture<Void> asyncIncrementWithAtomicInteger(int id) {
        return CompletableFuture.runAsync(() -> {
            int existingValue = getValue();
            log.info("before asyncIncrementWithAtomicInteger::id:{}::counter::{}",id,existingValue);
            int newValue = existingValue + 1;
            do{

            }while(atomicCounter.compareAndSet(existingValue, newValue));
            log.info("after asyncIncrementWithAtomicInteger::id:{}::counter::{}",id,newValue);
        });
    }



}
