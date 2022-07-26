package com.example.airplaneandbusonlineticketapi.dto;

import com.example.airplaneandbusonlineticketapi.model.enums.GenderType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class IndividualUserDto {
    private String name;
    private String surname;
    private String password;
    private String email;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    private Integer age;
}
