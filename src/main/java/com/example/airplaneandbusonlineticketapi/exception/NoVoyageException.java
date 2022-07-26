package com.example.airplaneandbusonlineticketapi.exception;

public class NoVoyageException extends RuntimeException {
    public NoVoyageException() {
        super("There is no voyage with this information in the system.");
    }
}
