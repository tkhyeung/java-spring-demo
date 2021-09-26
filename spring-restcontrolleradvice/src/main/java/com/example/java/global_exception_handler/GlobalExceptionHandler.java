package com.example.java.global_exception_handler;

import com.example.java.controller.response.AlphabetErrorResponse;
import com.example.java.exception.ApplicationException;
import com.example.java.exception.ErrorCode;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity<AlphabetErrorResponse> handleApplicationException(ApplicationException e) {
        AlphabetErrorResponse error = AlphabetErrorResponse.builder()
                .errorCode(e.getErrorCode().name())
                .errorMsg(e.getMessage())
                .timestamp(LocalDateTime.now())
                .status(getHttpStatus(e.getErrorCode()).value())
                .build();
        return new ResponseEntity<>(error, getHttpStatus(e.getErrorCode()));
    }

    @ExceptionHandler(value = NotImplementedException.class)
    public ResponseEntity<AlphabetErrorResponse> handleNotImplementedException(NotImplementedException e) {
        AlphabetErrorResponse error = AlphabetErrorResponse.builder()
                .errorCode("Not implemented")
                .errorMsg(e.getMessage())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_IMPLEMENTED.value())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<AlphabetErrorResponse> handleRuntimeException(RuntimeException e) {
        AlphabetErrorResponse error = AlphabetErrorResponse.builder()
                .errorCode("RuntimeException")
                .errorMsg(e.getMessage())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private HttpStatus getHttpStatus(ErrorCode errorCode) {
        if (errorCode.equals(ErrorCode.NOT_FOUND)) {
            return HttpStatus.NOT_FOUND;
        } else if (errorCode.equals(ErrorCode.ID_FORMAT_ERROR)) {
            return HttpStatus.BAD_REQUEST;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
