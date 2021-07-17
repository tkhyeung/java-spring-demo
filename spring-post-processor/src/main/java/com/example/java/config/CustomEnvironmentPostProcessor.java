package com.example.java.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * Demonstrate adding(or altering) property in Environment Post Processor
 */
@Order(Ordered.LOWEST_PRECEDENCE)
public class CustomEnvironmentPostProcessor implements EnvironmentPostProcessor {

    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        System.out.println("CustomEnvironmentPostProcessor");
        // logger cannot be used here
        // if log is required, see deferred log
        Map<String, Object> map = new HashMap<>();
        map.put("magic.value", "2021_06");
        environment.getPropertySources()
                .addLast(new MapPropertySource("custom_property", map));

    }
}
