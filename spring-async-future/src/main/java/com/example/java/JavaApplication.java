package com.example.java;

import com.example.java.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Demonstrate usage of completable future.
 */
@SpringBootApplication
@Slf4j
public class JavaApplication implements CommandLineRunner {

    @Autowired
    private AsyncService service;

    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        test();
        log.info("--------------------------------");
        test();
        log.info("--------------------------------");
        test();
        log.info("--------------------------------");
        test();
        log.info("--------------------------------");
        test();
    }

    private void test() {
        log.info("1");
        CompletableFuture<Integer> testOne = service.asyncTest1();
        log.info("2");
        CompletableFuture<Integer> testTwo = service.asyncTest2();
        log.info("3");
        CompletableFuture<Integer> testThree = service.asyncTest3();
        log.info("4");
        CompletableFuture<Integer> testFour = service.asyncTest4();
        log.info("5");
        List<Integer> result = Stream.of(testOne, testTwo, testThree, testFour).map(CompletableFuture::join).collect(Collectors.toList());
        log.info("6");
        log.info(result.toString());
        log.info("7");

    }
}
