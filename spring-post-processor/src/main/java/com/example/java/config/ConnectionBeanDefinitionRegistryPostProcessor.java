package com.example.java.config;

import com.example.java.Connection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Demonstrate the usage of Binder to read application.yml and create Bean using BeanDefinition
 */
@Component
@Slf4j
public class ConnectionBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        log.info("postProcessBeanDefinitionRegistry");
        final String propertyToBeBinded = "spring.connections";
        List<ConnectionObject> list = Binder.get(environment).bind(propertyToBeBinded,
                Bindable.listOf(ConnectionObject.class))
                .orElse(null);
        if (list == null) return;
        for (ConnectionObject c : list) {
            log.info("BeanDefinitionRegistryPostProcessor: Adding BeanDefinition for {}", c.getName());

            AbstractBeanDefinition definition = BeanDefinitionBuilder.genericBeanDefinition(Connection.class,
                    () -> createConnectionObj(c.getUsername(), c.getPassword(), c.getUrl(), c.getPort()))
                    .getBeanDefinition();
            beanDefinitionRegistry.registerBeanDefinition(c.getName(), definition);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    private Connection createConnectionObj(String username, String password, String url, Integer port){
        Connection c = new Connection();
        c.setUsername(username);
        c.setPassword(password);
        c.setUrl(url);
        if(port != null) c.setPort(port);
        return c;
    }
}
