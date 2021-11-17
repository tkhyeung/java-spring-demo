package com.example.java;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "test.props")
@Getter
@Setter
@Configuration
public class ConfigurationPropertiesTestClass {
    List<String> list;
    Map<String, String> same;
}
