package com.example.airplaneandbusonlineticketapi.exception;

public class NoAdminException extends RuntimeException {
    public NoAdminException() {
        super("There is no admin with this information in the system.");
    }
}
