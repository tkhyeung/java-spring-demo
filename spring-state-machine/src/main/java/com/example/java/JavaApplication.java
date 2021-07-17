package com.example.java;

import com.example.java.config.BookEvent;
import com.example.java.config.BookState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import reactor.core.publisher.Mono;


/**
 * Implement workflow using spring-state-machine
 */
@SpringBootApplication
@Slf4j
public class JavaApplication implements CommandLineRunner {

    @Autowired
    private StateMachine<BookState, BookEvent> stateMachine;

    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
//        stateMachine.start();
//        stateMachine.sendEvent(BookEvent.RETURN);
//        stateMachine.sendEvent(BookEvent.BORROW);
//        stateMachine.stop();
        log.info("*****State machine started*****");
        stateMachine.startReactively().block();
        log.info("*****State machine send RETURN*****");
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(BookEvent.RETURN).build())).blockLast();
        log.info("*****State machine send BORROW*****");
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(BookEvent.BORROW).build())).blockLast();
        log.info("*****State machine send BORROW*****");
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(BookEvent.BORROW).build())).blockLast();
        log.info("*****State machine send RETURN*****");
        stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(BookEvent.RETURN).build())).blockLast();
        stateMachine.stopReactively().block();
        log.info("*****State machine ended*****");
    }
}
