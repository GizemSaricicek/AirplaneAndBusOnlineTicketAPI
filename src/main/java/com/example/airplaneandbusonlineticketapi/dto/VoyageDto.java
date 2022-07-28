package com.example.airplaneandbusonlineticketapi.dto;

import com.example.airplaneandbusonlineticketapi.model.enums.CurrencyType;
import com.example.airplaneandbusonlineticketapi.model.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class VoyageDto {
    private String country;
    private LocalDateTime voyageDate;
    private VehicleType type;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
}
