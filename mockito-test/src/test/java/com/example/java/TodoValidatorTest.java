package com.example.java;

import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
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

    @Test
    void testShouldReturnThrowsErrorWithMockingStaticMethod() {
        //given
        String id = "abc";
        MockedStatic<StringUtils> stringUtilsMockedStatic = Mockito.mockStatic(StringUtils.class);
        stringUtilsMockedStatic.when(() ->
            StringUtils.isNumeric(id)
        ).thenReturn(false);
        //when
//        doThrow(new IllegalArgumentException()).when(validator).validate(eq(id));
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.validate(id));
        //then
//        verify(validator, times(1)).validate(id);
        stringUtilsMockedStatic.verify(() -> StringUtils.isNumeric(id));
        stringUtilsMockedStatic.close();
    }

    @Test
    void testShouldReturnNothingWhenValidateIsOkWithMockingStaticMethod() {
        //given
        String id = "1";
        MockedStatic<StringUtils> stringUtilsMockedStatic = Mockito.mockStatic(StringUtils.class);
        stringUtilsMockedStatic.when(() ->
                StringUtils.isNumeric(id)
        ).thenReturn(true);

        //when
//        doNothing().when(validator).validate(eq(id));
        validator.validate(id);
        //then
//        verify(validator, times(1)).validate(id);
        stringUtilsMockedStatic.verify(() -> StringUtils.isNumeric(id));
        stringUtilsMockedStatic.close();

    }





}
