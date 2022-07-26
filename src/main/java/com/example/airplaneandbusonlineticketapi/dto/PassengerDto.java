package com.example.airplaneandbusonlineticketapi.dto;

import com.example.airplaneandbusonlineticketapi.model.enums.GenderType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class PassengerDto {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    private Integer age;

}
