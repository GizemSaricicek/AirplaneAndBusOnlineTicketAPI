package com.example.airplaneandbusonlineticketapi.dto;

import com.example.airplaneandbusonlineticketapi.model.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class VoyageDto {
    private String country;
    private LocalDateTime voyageDate;
    private VehicleType type;
    private Double money;
}
