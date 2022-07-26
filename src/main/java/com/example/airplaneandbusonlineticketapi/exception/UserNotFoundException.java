package com.example.airplaneandbusonlineticketapi.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found.");
    }
}
