package com.example.java.config;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ListPatternValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateList {
    String pattern() default "";
    String message() default "Invalid List";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}