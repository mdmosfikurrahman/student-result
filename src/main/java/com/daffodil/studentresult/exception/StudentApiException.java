package com.daffodil.studentresult.exception;

import lombok.Getter;

@Getter
public class StudentApiException extends RuntimeException {

    private final int statusCode;

    public StudentApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}