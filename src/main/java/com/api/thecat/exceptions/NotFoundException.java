package com.api.thecat.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String exception) {
        super(exception);
    }
}
