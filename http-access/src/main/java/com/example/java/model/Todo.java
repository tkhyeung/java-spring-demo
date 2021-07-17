package com.example.java.model;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Todo {
    private String userId;
    private String id;
    private String title;
    private String completed;

}
