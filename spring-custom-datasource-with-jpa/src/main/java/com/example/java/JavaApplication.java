package com.example.java;

import com.example.java.entity.HumanEntity;
import com.example.java.eternals.EternalsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.sql.DataSource;


/*
    - Custom datasource based on profile
    - Build jpa repository, entity manager, transaction manager and datasource at runtime
    - Test ConfigurationProperties
 */
@Slf4j
@SpringBootApplication
@PropertySources({
        @PropertySource("classpath:application-eternals.properties"),
        @PropertySource("classpath:application-avengers.properties"),
        @PropertySource("classpath:application.properties")
})
//@ComponentScan(basePackages = {"com.example.java"})
public class JavaApplication implements CommandLineRunner {

    private static String PROFILE_NAME = "--spring.profiles.active=";
    private static String ACTIVE_PROFILE_ETERNALS = "eternals";
    private static String ACTIVE_PROFILE_AVENGERS = "avengers";

    public static void main(String[] args) {
        String[] newArgs = new String[args.length + 1];
        System.arraycopy(args, 0, newArgs, 0, args.length);
        newArgs[args.length] = PROFILE_NAME + ACTIVE_PROFILE_AVENGERS;
        SpringApplication.run(JavaApplication.class, newArgs);
    }

    @Autowired
    DataSource dataSource;

    @Autowired
    EternalsProperties properties;

    @Value("${spring.profiles.active}")
    String profile;

    @Autowired
    ConfigurableEnvironment environment;

    @Autowired
    HumanRepository repository;

    @Autowired
    ConfigurationPropertiesTestClass configurationPropertiesTestClass;

    @Override
    public void run(String... args) throws Exception {
        log.info("profile property:{}", profile);
        log.info("Active profile:{}", environment.getActiveProfiles());
        log.info("Eternals properties:{}", properties);
        log.info("Datasource is {}", dataSource);
        repository.save(HumanEntity.builder().name("Dane").age(27).build());
        repository.findAll().forEach(e -> log.info("human:{}", e));
        log.info("test configurationproperty list: {}", configurationPropertiesTestClass.getList());
        log.info("test configurationproperty same: {}", configurationPropertiesTestClass.getSame());
    }
}
