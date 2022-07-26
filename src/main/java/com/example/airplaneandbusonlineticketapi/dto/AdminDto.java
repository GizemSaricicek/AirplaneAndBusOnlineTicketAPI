package com.example.airplaneandbusonlineticketapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AdminDto {
    private Integer id;
    private String name;
    private String surname;
    private String password;
    private String email;
}
