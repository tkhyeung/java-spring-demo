package com.example.java;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TodoValidator {

    void validate(String id) {
        Optional<String> result = validateId(id);
        if (result.isPresent()) {
            throw new IllegalArgumentException(result.get());
        }
    }

    private Optional<String> validateId(String id) {
        if (StringUtils.isNumeric(id)) {
            return Optional.empty();
        } else {
            return Optional.of("not a valid id " + id);
        }
    }

}
