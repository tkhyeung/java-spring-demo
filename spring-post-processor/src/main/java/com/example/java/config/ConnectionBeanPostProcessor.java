package com.example.java.config;

import com.example.java.Connection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;


/**
 * Demonstrate how to alter a bean after it created
 */
@Slf4j
@Component
public class ConnectionBeanPostProcessor implements BeanPostProcessor {

    private static final String beanNameToPlayWith = "bean1";

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(beanNameToPlayWith.equals(beanName)){
            String url = ((Connection) bean).getUrl();
            log.info("BeanPostProcessor: Altering bean {}", beanName);
            ((Connection) bean).setUsername(url + " !haha!(added in BeanPostProcessor)");
        }
        return bean;
    }
}
