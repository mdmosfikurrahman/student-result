package com.daffodil.studentresult.exception;

import lombok.Getter;

@Getter
public class StudentApiException extends RuntimeException {

    private final int statusCode;

    public StudentApiException(String message, Throwable cause, int statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }
}