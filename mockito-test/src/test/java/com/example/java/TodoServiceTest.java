package com.example.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest extends TodoTest {

    @InjectMocks
    TodoService service;
    @Mock
    TodoWebclient client;
    @Mock
    TodoValidator validator;

    @Test
    void testShouldReturnTodoById() {
        //Given
        String idStr = "1";
        int id = 1;
        Todo todoTestData = getTodo();
        when(client.getTodos(id)).thenReturn(todoTestData);
        //When
        Todo todo = service.findTodoById(idStr);
        //Then
        Assertions.assertNotNull(todo);
        Assertions.assertEquals(todoTestData, todo);
        verify(validator, times(1)).validate(idStr);
        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(client, times(1)).getTodos(idCaptor.capture());
        Assertions.assertEquals(id, idCaptor.getValue());
    }

    private static Stream<Arguments> testData() {
        return Stream.of(Arguments.of(1,"1"), Arguments.of(2,"2"));
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testShouldReutrnTodoById(int id, String idStr) {
        //given
        Todo todoTestData = getTodo();
        when(client.getTodos(id)).thenReturn(todoTestData);
        //When
        Todo todo = service.findTodoById(idStr);
        //Then
        Assertions.assertNotNull(todo);
        Assertions.assertEquals(todoTestData, todo);
        verify(validator, times(1)).validate(idStr);
        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(client, times(1)).getTodos(idCaptor.capture());
        Assertions.assertEquals(id, idCaptor.getValue());

    }

}
