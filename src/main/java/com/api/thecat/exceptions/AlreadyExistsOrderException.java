package com.api.thecat.exceptions;

public class AlreadyExistsOrderException extends RuntimeException {
    public AlreadyExistsOrderException(String exception) {
        super(exception);
    }
}
