package com.example.java.exception;

public enum ErrorCode {
    NOT_FOUND("0001", "not found"),
    ID_FORMAT_ERROR("0002", "id format error"),
    OTHER("9999", "OTHER EXCEPTION")
    ;
    String code;
    String description;

    //default private
    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
