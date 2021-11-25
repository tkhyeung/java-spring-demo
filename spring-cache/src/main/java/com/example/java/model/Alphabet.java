package com.example.java.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Alphabet {
    private int id;
    private String value;
    private String origin;

    public static Alphabet createGreekAlphabet(int id, String value) {
        return Alphabet.builder()
                .id(id)
                .value(value)
                .origin("Greek")
                .build();
    }

    public static Alphabet createLatinAlphabet(int id, String value) {
        return Alphabet.builder()
                .id(id)
                .value(value)
                .origin("Latin")
                .build();
    }

}
