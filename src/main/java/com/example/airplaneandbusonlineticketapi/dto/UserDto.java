package com.example.airplaneandbusonlineticketapi.dto;

import com.example.airplaneandbusonlineticketapi.model.enums.UserType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UserDto {
    private String fullName;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String phoneNumber;
}
