package com.example.java;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "todo")
public class TodoProperties {
    private List<String> ids;
    private Map<String, String> maps;
    private int year;
    private boolean enabled;
}
