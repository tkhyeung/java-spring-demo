package com.example.java.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Can alter bean definition in BeanFactoryPostProcessor (Bean not created yet)
 */

@Slf4j
@Component
public class ConnectionBeanFactoryPostProcessor implements BeanFactoryPostProcessor, EnvironmentAware {

    private Environment environment;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("postProcessBeanFactory");
        final String propertyToBeBinded = "spring.beans";
        List<ConnectionObject> list = Binder.get(environment).bind(propertyToBeBinded,
                Bindable.listOf(ConnectionObject.class))
                .orElse(null);
        if (list == null) return;

        for (String beanName : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if(list.stream().anyMatch(s -> s.getName().equals(beanName))){
                log.info("BeanFactoryPostProcessor: getting BeanDefinition of {} {}", beanName, beanDefinition.toString());
            }
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}

