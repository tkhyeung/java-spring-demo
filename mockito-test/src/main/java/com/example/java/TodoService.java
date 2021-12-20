package com.example.java;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoWebclient webclient;
    private final TodoValidator validator;

    public Todo findTodoById(String id) {
        validator.validate(id);
        return webclient.getTodos(map(id));
    }

    private static int map(String id) {
        return Integer.parseInt(id);
    }


}
