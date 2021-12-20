package com.example.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = TodoProperties.class)
@TestPropertySource(properties = {
        "todo.ids=1,2,3,4,5,6",
        "todo.maps.name=hello",
        "todo.maps.completed=true",
        "todo.year=2021",
        "todo.enabled=false"
})
public class TodoPropertiesTest {
    @Autowired
    TodoProperties properties;

    @Test
    void testShouldReturnIds() {
        //given
        List<String> result = List.of("1", "2", "3", "4", "5", "6");
        //when
        List<String> ids = properties.getIds();
        //then
        Assertions.assertNotNull(ids);
        result.stream().forEach(i -> Assertions.assertEquals(i, ids.get(result.indexOf(i))));
    }

    @Test
    void testShouldReturnMaps() {
        //given
        Map<String, String> result = new HashMap<>();
        result.put("name", "hello");
        result.put("completed", "true");
        //when
        Map<String, String> map = properties.getMaps();
        //then
        Assertions.assertNotNull(map);
        result.forEach((key, value) -> Assertions.assertEquals(value, map.get(key)));
    }

    @Test
    void testShouldReturnYear() {
        //given
        int result = 2021;
        //when
        int year = properties.getYear();
        //then
        Assertions.assertEquals(result, year);
    }

    @Test
    void testShouldReturnEnabled() {
        //given
        //when
        boolean enabled = properties.isEnabled();
        //then
        Assertions.assertFalse(enabled);
    }



}
