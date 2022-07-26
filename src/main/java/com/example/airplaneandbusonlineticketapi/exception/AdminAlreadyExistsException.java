package com.example.airplaneandbusonlineticketapi.exception;

public class AdminAlreadyExistsException extends RuntimeException {
    public AdminAlreadyExistsException() {
        super("Admin already exists.");
    }
}
