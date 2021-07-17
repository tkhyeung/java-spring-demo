package com.example.java;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Demonstrate some operations related to yaml
 * <p>
 * 1. Using YamlPropertiesFactoryBean to read yaml and load into property
 * <p>
 * 2. Resolving custom placeholder in yml using PropertySourcesPlaceholderConfigurer
 *
 */
@SpringBootApplication
@Slf4j
public class JavaApplication implements CommandLineRunner {

    @Value("${bcd.value}")
    String value;

    @Value("${connection.password}")
    String decPassword;

    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        log.info("bcd.value is {}", value);
        log.info("connection.password is {}", decPassword);
//        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
//        yaml.setResources(new ClassPathResource("my_custom_application.yaml"));
//        yaml.setSingleton(true);
//        yaml.afterPropertiesSet();
//        Properties properties = yaml.getObject();
//        log.info(properties.toString());
//        log.info("bcd.value is {}", properties.get("bcd.value").toString());
    }
}
