package com.example.airplaneandbusonlineticketapi.dto;

import lombok.Data;

@Data
public class CorporateUserDto {
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
}
