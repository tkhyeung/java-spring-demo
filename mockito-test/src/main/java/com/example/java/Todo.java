package com.example.java;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Todo {
    private String userId;
    private String id;
    private String title;
    private String completed;

}
