package com.example.java.exception;

public class ApplicationException extends RuntimeException{

    private ErrorCode errorCode;
    public ApplicationException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }


    public ApplicationException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ApplicationException(Throwable cause) {
        super(cause);
        this.errorCode = ErrorCode.OTHER;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
