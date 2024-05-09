package com.bankSystem.exception;

public class InternalServerException extends RuntimeException {

    public InternalServerException() {}

    public InternalServerException(String message) {
        super(message);
    }
}
