package com.example.java;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.netty.handler.logging.LogLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import java.time.Duration;
import java.util.stream.IntStream;

//circuit breaker resilience 4j
//https://docs.spring.io/spring-cloud-circuitbreaker/docs/current/reference/html/
//https://stackoverflow.com/questions/64051144/webflux-webclient-retry-and-spring-cloud-circuit-breaker-resilience4j-retry-patt
//https://resilience4j.readme.io/docs/getting-started-3#section-annotations
//resilience4j springboot supports annotation
//spring cloud circuitbreaker does not support annotation
@Slf4j
@SpringBootApplication
@PropertySources({
        @PropertySource("classpath:application.yml")
})

public class JavaApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }

    @Value("${test.test}")
    String props;
    @Override
    public void run(String... args) throws Exception {
        log.info("{}", props);
    }

}
