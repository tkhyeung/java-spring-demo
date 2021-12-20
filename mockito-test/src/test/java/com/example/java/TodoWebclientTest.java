package com.example.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoWebclientTest extends TodoTest{

    @InjectMocks
    TodoWebclient todoWebclient;
    @Mock
    WebClient webClient;
    @Mock
    WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock
    WebClient.RequestHeadersSpec requestHeadersSpec;
    @Mock
    WebClient.ResponseSpec responseSpec;

    @BeforeEach
    void testSetUp() {
        ReflectionTestUtils.setField(todoWebclient, "webClient", webClient);
    }

    @Test
    void testShouldReturnTodos() {
        //Given
        int id = 1;
        Todo todoTestData = getTodo();
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Todo.class)).thenReturn(Mono.just(todoTestData));
        //When
        Todo todo = todoWebclient.getTodos(id);
        //Then
        verify(webClient, times(1)).get();
        ArgumentCaptor<String> endpointCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> idCaptor = ArgumentCaptor.forClass(String.class);
        verify(requestHeadersUriSpec, times(1)).uri(endpointCaptor.capture(), idCaptor.capture());
        Assertions.assertEquals("/todos/{id}", endpointCaptor.getValue());
        Assertions.assertEquals(Integer.toString(id), idCaptor.getValue());
        verify(requestHeadersSpec, times(1)).retrieve();
        verify(responseSpec, times(1)).bodyToMono(Todo.class);
        Assertions.assertEquals(todoTestData.getCompleted(), todo.getCompleted());
        Assertions.assertEquals(todoTestData.getId(), todo.getId());
        Assertions.assertEquals(todoTestData.getTitle(), todo.getTitle());
        Assertions.assertEquals(todoTestData.getUserId(), todo.getUserId());
    }

}
