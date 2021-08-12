package com.example.java;

import com.example.java.service.AsyncService;
import com.example.java.service.AsyncServiceUsingAtomicVariable;
import com.example.java.service.AsyncServiceUsingSynchronized;
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

    @Autowired
    private AsyncServiceUsingAtomicVariable serviceUsingAtomicVariable;

    @Autowired
    private AsyncServiceUsingSynchronized serviceUsingSynchronized;



    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        test();
//        log.info("--------------------------------");
//        test();
//        log.info("--------------------------------");
//        test();
//        log.info("--------------------------------");
//        test();
//        log.info("--------------------------------");
//        test();
        testAtmoic();
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

    private void testAtmoic(){
        log.info("test Atomic::");


        Stream.of(
                serviceUsingAtomicVariable.add(4,1),
                serviceUsingAtomicVariable.add(3,2),
                serviceUsingAtomicVariable.add(5,3),
                serviceUsingAtomicVariable.minus(7,4),
                serviceUsingAtomicVariable.minus(1,5)
        ).map(CompletableFuture::join).collect(Collectors.toList());

        log.info("count is {}", serviceUsingAtomicVariable.getValue());


        // atomic variable uses compare and swap algorithm, non block, optimistic locking;
        // Atomic variable internally does not use lock, henus no thread suspension and resumption
        // synchronised uses locks, costly
        // should use synchronised to all accessible methods

        // https://www.geeksforgeeks.org/difference-between-atomic-volatile-and-synchronized-in-java/
        //Instance methods
        //Static methods
        //Code blocks (code blocks with in a method)

        //todo reentrant lock
        //https://stackoverflow.com/questions/11821801/why-use-a-reentrantlock-if-one-can-use-synchronizedthis

//        Stream.of(
//                serviceUsingSynchronized.add(4,1),
//                serviceUsingSynchronized.add(3,2),
//                serviceUsingSynchronized.add(5,3),
//                serviceUsingSynchronized.minus(7,4),
//                serviceUsingSynchronized.minus(1,5)
//        ).map(CompletableFuture::join).collect(Collectors.toList());
//
//        log.info("count is {}", serviceUsingSynchronized.getValue());

    }
}
