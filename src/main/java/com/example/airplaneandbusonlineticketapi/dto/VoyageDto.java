package com.example.airplaneandbusonlineticketapi.dto;

import com.example.airplaneandbusonlineticketapi.model.enums.CurrencyType;
import com.example.airplaneandbusonlineticketapi.model.enums.VehicleType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

public class VoyageDto {
    private Integer id;
    private String country;
    private String departure;
    private LocalDateTime voyageDate;
    private VehicleType type;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

}
