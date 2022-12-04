package com.enigma.Exception;

public class EntityExistException extends RuntimeException {
    public EntityExistException() {
        super("Data is existt");
    }

    public EntityExistException(String message) {
        super(message);
    }
}
