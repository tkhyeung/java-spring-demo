package com.example.java;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.netty.handler.logging.LogLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import java.time.Duration;
import java.time.LocalDateTime;

//https://github.com/resilience4j/resilience4j-spring-boot2-demo/tree/master/src/main/java/io/github/robwin
@Slf4j
@Service
public class TestService {

    private static final String CIRCUIT_BREAKER_TEST = "backend_test";

    private Mono<String> normalGet() {
        log.info("normal get! " + LocalDateTime.now());
        return getWebClient().get().uri("/todos/{id}", "1")
                .retrieve().bodyToMono(String.class);
    }
                                                                                // if open then fail fast
    @CircuitBreaker(name = CIRCUIT_BREAKER_TEST, fallbackMethod = "fallback") // exception then go to fallback method,  exceed threshold then open
//    @RateLimiter(name = CIRCUIT_BREAKER_TEST)                                 //number of permissions available during one limit refresh period
//    @Bulkhead(name = CIRCUIT_BREAKER_TEST)                                  //to  limit the number of concurrent execution
//    @Retry(name = CIRCUIT_BREAKER_TEST, fallbackMethod = "fallback_retry")         //retry
    @TimeLimiter(name = CIRCUIT_BREAKER_TEST)                                 //if time limit exceed then go to fallback, exceed threshold then open
    public Mono<String> get(Mode choice) {
        if (Mode.NORMAL.equals(choice)) {
            return normalGet();
        } else if (Mode.TIMEOUT.equals(choice)) {
            return timedOutGet();
        } else {
            return failedGet();
        }
    }



    private Mono<String> failedGet() {
        log.info("failed! " + LocalDateTime.now());
        return Mono.error(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "This is a remote exception " + LocalDateTime.now()));
    }

    private Mono<String> timedOutGet() {
        log.info("timeout! " + LocalDateTime.now());
        return Mono.just("Hello World i am timed out")
                .delayElement(Duration.ofSeconds(10));

    }

    private Mono<String> fallback(Mode mode, Exception e) {
        log.info("i am fallback! with Mode:{}", mode.name());
        log.error("" + e);
        return Mono.just("fallback");
    }

    private Mono<String> fallback_retry(Mode mode, Exception e) {
        log.info("i am fallback_retry! with Mode:{}", mode.name());
        log.error("" + e);
        return Mono.just("fallback_retry!");
    }


    private WebClient webClient;

    private WebClient getWebClient() {
        if (webClient != null) return webClient;
        HttpClient httpClient = HttpClient
                .create()
                .wiretap(this.getClass().getCanonicalName(),
                        LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL);
        webClient = WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
        return webClient;
    }


}
