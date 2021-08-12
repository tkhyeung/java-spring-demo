package com.example.java.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AsyncServiceUsingSynchronized {

    public static class AtomicVariableTest{
        private int count = 0;

        public synchronized void add(int num, int id){
            log.info("id:{} adding {} to {}", id, num, count);
//            count = count + num;

            int currentValue = count;
            sleep(1);
            int newValue = currentValue + num;
            count = newValue;
        }


        public synchronized void minus(int num, int id){
            log.info("id:{} subtracting {} from {}", id, num, count);
//            count = count - num;
            int currentValue = count;
            sleep(1);
            int newValue = currentValue - num;
            count = newValue;

        }

        public synchronized int get(){
            return count;
        }

        private static void sleep(int second){
            try {
                TimeUnit.SECONDS.sleep(second);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private AtomicVariableTest test = new AtomicVariableTest();

    @Async
    public CompletableFuture<Void> add(int num, int id){
        return CompletableFuture.runAsync( () -> {
            test.add(num,id);
        });
    }

    @Async
    public CompletableFuture<Void> minus(int num, int id){
        return CompletableFuture.runAsync( () -> {
            test.minus(num,id);
        });
    }

    public int getValue(){
        return test.get();
    }

}
