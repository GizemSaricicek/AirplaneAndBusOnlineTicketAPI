package com.example.airplaneandbusonlineticketapi.exception;

public class CannotBuyTicketException extends RuntimeException {
    public CannotBuyTicketException() {
        super("Cannot buy a ticket for this voyage.");
    }
}
