package com.example.ticketpaymentservice.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {

    private String message;
    private LocalDateTime localDateTime;

    public ExceptionResponse(String message, LocalDateTime localDateTime) {
        this.message = message;
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String toString() {
        return "ExceptionResponse{" +
                "message='" + message + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
