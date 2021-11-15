package com.example.java.avengers;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ToString
@Setter
@Getter
@ConfigurationProperties(prefix = "avengers.datasource")
public class AvengersProperties {

    private String url;
    private String username;
    private String password;

}
