package com.company.exeption;

public class EntityAlreadyExistsException extends RuntimeException {

    private String message;

    public EntityAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
