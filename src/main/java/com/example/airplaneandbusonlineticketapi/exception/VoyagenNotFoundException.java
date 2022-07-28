package com.example.airplaneandbusonlineticketapi.exception;

public class VoyagenNotFoundException extends RuntimeException {
    public VoyagenNotFoundException() {
        super("There is no voyage with this information in the system.");
    }
}
