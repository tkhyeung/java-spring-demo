package com.example.java.service;

import com.example.java.model.User;
import com.example.java.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Optional<User> getUserByUsername(String username) {
        log.info("inside getUserByUsername, username:{}", username);
        return repository.findByUsername(username);
    }

    public Optional<User> getUserByToken(String token) {
        log.info("inside getUserByToken, token:{}", token);
        return repository.findByToken(token);
    }

    public Optional<User> setToken(String username, String token) {
        log.info("inside setToken, username:{}, token:{}", username, token);
        Optional<User> userOptional = repository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setToken(token);
            repository.save(user);
            return Optional.of(user);
        } else {
            throw new RuntimeException("User not found for userName:" + username);
        }
    }

    public Optional<List<User>> getAllUsers() {
        return Optional.of(repository.findAll());
    }

    public boolean isTokenValid(String token) {
        log.info("inside isTokenValid, token:{}", token);
        Optional<User> userOptional = getUserByToken(token);
        if (userOptional.isPresent()) {
            return true;
        } else {
            log.info("inside isTokenValid, cannot find user with token:{}; returning false", token);
            return false;
        }
    }
}
