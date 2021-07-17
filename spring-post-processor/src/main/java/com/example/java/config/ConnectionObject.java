package com.example.java.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Container object for binding
 */
@Getter
@Setter
@ToString
public class ConnectionObject {
    private String name;
    private String username;
    private String password;
    private String url;
    private Integer port;
}
