package com.example.java.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class AlphabetResponse {
    private int id;
    private String letter;
}
