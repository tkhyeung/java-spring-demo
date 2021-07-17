package com.example.java.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.boot.origin.OriginTrackedValue;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CustomPropertySourcesPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {

    private static final String CUSTOM_PLACEHOLDER_PREFIX = "custom_enc(";
    private static final String CUSTOM_PLACEHOLDER_SUFFIX = ")";

    @Override
    public void setEnvironment(Environment environment) {
        super.setEnvironment(environment);
        resolvePlaceholder((ConfigurableEnvironment) environment);
    }

    private void resolvePlaceholder(ConfigurableEnvironment environment){
        log.info("CustomPropertySourcesPlaceholderConfigurer: Start modifying property source");
        MutablePropertySources propertySources = environment.getPropertySources();
        String name = null; // only tested for one application.yml

        Map<String, Object> decMap = new HashMap<>();

        for(PropertySource p: propertySources){
            if(p instanceof OriginTrackedMapPropertySource){ //application.yml
                name = p.getName();
                Map<String, Object> map = (Map<String, Object>) p.getSource();
                map.forEach((k,v) -> {
                    decMap.put(k, resolve(v));
                });
            }
        }
        //replace property source (original one is immutable)
        OriginTrackedMapPropertySource originTrackedMapPropertySource =
                new OriginTrackedMapPropertySource(name, decMap);
        propertySources.replace(name, originTrackedMapPropertySource);
    }

    private OriginTrackedValue resolve(Object enc){
        if (enc == null) return null;

        OriginTrackedValue o = (OriginTrackedValue) enc;
        if(o.getValue() instanceof CharSequence){
            String encStr = ((CharSequence) o.getValue()).toString();
            if((encStr.length() > (CUSTOM_PLACEHOLDER_PREFIX.length() + CUSTOM_PLACEHOLDER_SUFFIX.length()) ) &&
                encStr.startsWith(CUSTOM_PLACEHOLDER_PREFIX) && encStr.endsWith(CUSTOM_PLACEHOLDER_SUFFIX)){
                log.info("resolve {}}", enc);
                //put logic below
                String dec =  "decrypted password!";
                return OriginTrackedValue.of(dec , o.getOrigin());
            }
        }
        return o;
    }

}
