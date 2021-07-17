package com.example.java;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Connection {
    private String username;
    private String password;
    private String url;
    private int port = 22;

    public void printConnection(){
        log.info("username: {}; password: {}, url: {}, port: {}",
                username, password, url, port);
    }

}
