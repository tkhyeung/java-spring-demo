package com.example.java;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoValidatorTest {

//    @Spy
    TodoValidator validator = new TodoValidator();

    @Test
    void testShouldReturnNothingWhenValidateIsOk() {
        //given
        String id = "1";
        //when
//        doNothing().when(validator).validate(eq(id));
        validator.validate(id);
        //then
//        verify(validator, times(1)).validate(id);
    }

    @Test
    void testShouldReturnThrowsError() {
        //given
        String id = "abc";
        //when
//        doThrow(new IllegalArgumentException()).when(validator).validate(eq(id));
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.validate(id));
        //then
//        verify(validator, times(1)).validate(id);
    }



}
