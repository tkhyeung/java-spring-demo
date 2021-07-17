package com.example.java.model;

import lombok.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private String id;
    private String title;
    private String body;
    private String userId;
}
