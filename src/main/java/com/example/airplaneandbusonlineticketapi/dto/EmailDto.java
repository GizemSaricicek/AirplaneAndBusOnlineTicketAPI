package com.example.airplaneandbusonlineticketapi.dto;

public class EmailDto {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmailDto(String email) {
        this.email = email;
    }
}
