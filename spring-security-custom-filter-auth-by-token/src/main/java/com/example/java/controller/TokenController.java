package com.example.java.controller;


import com.example.java.model.User;
import com.example.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private UserService userService;

    @PostMapping("/{username}")
    public Optional<User> setTokenForUser(@PathVariable String username) {
        return userService.setToken(username, UUID.randomUUID().toString());
    }

    @GetMapping("/users")
    public Optional<List<User>> getAllUser() {
        return userService.getAllUsers();
    }

}
