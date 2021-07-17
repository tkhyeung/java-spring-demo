package com.example.java.service;

import com.example.java.JavaApplication;
import com.example.java.model.Post;
import com.example.java.model.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RestTemplateService {


    @Autowired
    private RestTemplate restTemplate;

    public void test() {
        log.info("Resttemplate test");
        test1();
        log.info("Resttemplate test end");
        return;
    }

    private void test1(){
        /**
         * Use Rest template to query api
         */

        //GET /todos/1
        ResponseEntity<Todo> response
                = restTemplate.getForEntity(JavaApplication.RESOURCE_URL + "/todos/1", Todo.class);
        log.info(response.getBody().toString());

        //GET /todos
        ResponseEntity<Todo[]> responses
                = restTemplate.getForEntity(JavaApplication.RESOURCE_URL + "/todos", Todo[].class);
        log.info(Arrays.stream(responses.getBody()).collect(Collectors.toList()).toString());

        //POST /posts
        HttpEntity<Post> request = new HttpEntity<>(
                Post.builder().body("bar").title("foo").userId("1").build()
        );
        Post post = restTemplate.postForObject(JavaApplication.RESOURCE_URL + "/posts", request, Post.class);
        log.info(post.toString());
    }

}
