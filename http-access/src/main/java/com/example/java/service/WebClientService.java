package com.example.java.service;

import com.example.java.JavaApplication;
import com.example.java.model.Post;
import com.example.java.model.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WebClientService {

    private WebClient webClient;

    public void test() {
        log.info("Webclient test");
        webClientBlocking(); //Use WebClient to query api
        webClientAsync(); //Use WebClient to query api (Async)
        log.info("Webclient test end");
        return;
    }

    private void webClientBlocking(){
        log.info("retrieve by blocking");
        webClient = WebClient.create(JavaApplication.RESOURCE_URL);

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
