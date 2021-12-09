package com.example.java.service;

import com.example.java.JavaApplication;
import com.example.java.model.Post;
import com.example.java.model.Todo;
import io.netty.handler.logging.LogLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WebClientService {

    private WebClient webClient;

    private ExchangeFilterFunction logRequest(){
        return ExchangeFilterFunction.ofRequestProcessor(request -> {
            StringBuilder sb = new StringBuilder("Request: \n");
            //append clientRequest method and url
            sb.append("method: " + request.method().name() + "\n");
            sb.append("url: " + request.url().toString()+ "\n");
            sb.append("header: ");
            request
                    .headers()
                    .forEach((name, values) -> values.forEach(value -> sb.append("key:"+name+", value:"+value+"; ")));
            sb.append("\n");
            sb.append("Body ");
            sb.append(request.body().toString());
            sb.append("\n");
            log.info(sb.toString());
            return Mono.just(request);
        });
    }
    private ExchangeFilterFunction logResponse(){
        return ExchangeFilterFunction.ofResponseProcessor(response -> Mono.just(response));
    }


    public void test() {
        log.info("Webclient test");
        HttpClient httpClient = HttpClient
                .create()
                .wiretap(this.getClass().getCanonicalName(),
                        LogLevel.INFO, AdvancedByteBufFormat.TEXTUAL);

        webClient = WebClient.builder()
                .baseUrl(JavaApplication.RESOURCE_URL)
//To log request response
//1. custom filter way
//                .filters(f -> f.add(logRequest()))
//                .filters(f -> f.add(logResponse()))
//2. suggested way with logging.level.reactor.netty.http.client.HttpClient: DEBUG
//                .codecs(configurer -> configurer.defaultCodecs().enableLoggingRequestDetails(true))
//3. with wiretap method
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        webClientBlocking(); //Use WebClient to query api
        webClientAsync(); //Use WebClient to query api (Async)
        log.info("Webclient test end");
        return;
    }

    private void webClientBlocking(){
        log.info("retrieve by blocking");

        //GET /todos/1
        Mono<Todo> response = webClient.get().uri("/todos/{id}", "1")
                    .retrieve().bodyToMono(Todo.class);
        log.info(response.block().toString());

        //GET /todos
        Mono<Todo[]> responses
                = webClient.get().uri("/todos").retrieve().bodyToMono(Todo[].class);
        log.info(Arrays.stream(responses.block()).collect(Collectors.toList()).toString());

        //POST /posts
        Mono<Post> request = webClient.post().uri("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(Post.builder().body("bar").title("foo").userId("1").build()), Post.class)
                .retrieve()
                .bodyToMono(Post.class);
        log.info(request.block().toString());
    }

    private void webClientAsync(){
        log.info("retrieve by subscribe (asynchronous)");
        //GET /todos/1
        Mono<Todo> response = webClient.get().uri("/todos/{id}", "1")
                .retrieve().bodyToMono(Todo.class);
        response.subscribe(s -> log.info("GET /todos/1\n" + s.toString()), throwable -> log.error(throwable.getMessage()));

        //GET /todos
        Mono<Todo[]> responses
                = webClient.get().uri("/todos").retrieve().bodyToMono(Todo[].class);
        responses.subscribe(s -> log.info("GET /todos\n" + Arrays.stream(s).collect(Collectors.toList()).toString())
                , throwable -> log.error(throwable.getMessage()));

        //POST /posts
        Mono<Post> request = webClient.post().uri("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(Post.builder().body("bar").title("foo").userId("1").build()), Post.class)
                .retrieve()
                .bodyToMono(Post.class);
        request.subscribe(s -> log.info("POST /posts\n" + s.toString())
                , throwable -> log.error(throwable.getMessage()));

    }

}
