package com.example.java.service;

import com.example.java.config.TestFeignConfig;
import com.example.java.model.Post;
import com.example.java.model.Todo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.example.java.JavaApplication.RESOURCE_URL;

@FeignClient(value = "TestFeign", url = RESOURCE_URL, configuration = TestFeignConfig.class)
public interface TestFeign {

//    @GetMapping(value = "/todos")
    @RequestMapping(method = RequestMethod.GET, value = "/todos")
    Todo[] getTodos();

//    @GetMapping(value = "/todos/{id}")
    @RequestMapping(method = RequestMethod.GET, value = "/todos/{id}")
    Todo getTodoById(@PathVariable("id") Long id);

//    @PostMapping(value = "/posts")
    @RequestMapping(method = RequestMethod.POST, value = "/posts")
    Post submitPost(@RequestBody Post post);

}
