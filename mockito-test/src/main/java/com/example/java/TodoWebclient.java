package com.example.java;

import io.netty.handler.logging.LogLevel;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

@Component
public class TodoWebclient {

    private WebClient webClient;

    Todo getTodos(int id) {
        return getWebClient()
                .get()
                .uri("/todos/{id}", Integer.toString(id))
                .retrieve()
                .bodyToMono(Todo.class)
                .block();
    }

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
