package com.example.java;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.Map;

/**
 * https://stackoverflow.com/questions/29743320/how-exactly-does-the-spring-beanpostprocessor-work
 * https://stackoverflow.com/questions/30455536/beanfactorypostprocessor-and-beanpostprocessor-in-lifecycle-events
 * Example to play with post processors
 * <p>
 * 1. Making @Qualifier able to resolve value from yaml
 * <p>
 * 2. Creating custom bean (by BeanDefinition) of same type. Use it with @Qualifier. (Autowriable)
 * <p>
 * 3. Altering a bean after instantiated by Spring
 * <p>
 * 4. Adding new property by EnviornmentPostProcessor
 * <p>
 * @see @AutowireCandidateResolverConfigurer
 *
 * @see @CustomBeanDefinitionRegistryPostProcessor
 *
 * @see @BeanImplCustomBeanPostProcessor
 *
 * @see @CustomEnvironmentPostProcessor
 *
 */
@SpringBootApplication
@Slf4j
public class JavaApplication implements CommandLineRunner {

    @Autowired
//    @Qualifier("bean1")
    @Qualifier("${spring.test.bean}") // configured in application.yml
    Bean b;

    @Autowired
    ConfigurableApplicationContext configurableApplicationContext;

    @Autowired
    @Qualifier("${spring.connections[0].name}")
    Connection connection1;
    @Autowired
    @Qualifier("${spring.connections[1].name}")
    Connection connection2;

    @Value("${magic.value}")
    String valueFromEnvironmentPostProcessor;


    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        b.printValue();

        //reading the properties from yaml
        MutablePropertySources propertySources = configurableApplicationContext.getEnvironment()
                .getPropertySources();
        for(PropertySource propertySource: propertySources){
            if(propertySource instanceof OriginTrackedMapPropertySource){ //OriginTrackedMapPropertySource is user input
                Map<String, Object> m = ((OriginTrackedMapPropertySource) propertySource).getSource();
                m.forEach((k, v) -> log.info("key: {}, value:{}", k, v.toString()));
            }
        }

        Map<String, Connection> connectionMap = configurableApplicationContext.getBeansOfType(Connection.class);
        log.info("Connection(s) created: {}", connectionMap.toString());
        connection1.printConnection();
        connection2.printConnection();

        log.info("magic.value(added in EnvironmentPostProcessor): {}", valueFromEnvironmentPostProcessor);

    }
}