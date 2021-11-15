package com.example.java.eternals;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ToString
@Setter
@Getter
@ConfigurationProperties(prefix = "eternals.datasource")
public class EternalsProperties {

    private String url;
    private String username;
    private String password;

}
