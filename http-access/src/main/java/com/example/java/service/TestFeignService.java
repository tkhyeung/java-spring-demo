package com.example.java.service;

import com.example.java.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TestFeignService {

    @Autowired
    private TestFeign testFeign;

    public void test(){
        log.info("Feign test");
        log.info("retrieve todo id 1");
        log.info(testFeign.getTodoById(1L).toString());

        log.info("retrieve todos");
        log.info(Arrays.stream(testFeign.getTodos()).collect(Collectors.toList()).toString());

        Post postToSubmit = Post.builder().body("bar").title("foo").userId("1").build();
        log.info("Submit post {}", postToSubmit);
        log.info(testFeign.submitPost(postToSubmit).toString());
    }
}
