package com.example.green_shadow.exception;

public class NoSuchEntityException extends RuntimeException {
    public NoSuchEntityException(String entity, String message) {
        super(entity + "with reference " + message + " not found");
    }
}
