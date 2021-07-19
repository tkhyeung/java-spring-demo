package com.example.java.config;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
public class ListPatternValidator implements
        ConstraintValidator<ValidateList, List<String>> {

    private Pattern pattern;

    @Override
    public void initialize(ValidateList constraintAnnotation) {
        String customPattern = constraintAnnotation.pattern();
        pattern = Pattern.compile(customPattern);
    }

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        return value.stream().allMatch(e -> pattern.matcher(e).matches());

    }

}
