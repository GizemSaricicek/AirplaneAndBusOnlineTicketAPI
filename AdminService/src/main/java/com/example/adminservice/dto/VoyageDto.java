package com.example.adminservice.dto;

import com.example.adminservice.model.enums.CurrencyType;
import com.example.adminservice.model.enums.VehicleType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

public class VoyageDto {

    private Integer id;
    private String departure; //kalkış şehri
    private String country;
    private LocalDateTime voyageDate;
    private VehicleType type;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
    private Boolean status;

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCountry() {
        return country;
    }

    public LocalDateTime getVoyageDate() {
        return voyageDate;
    }

    public VehicleType getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public String getDeparture() {
        return departure;
    }

    public VoyageDto(String country, LocalDateTime voyageDate, VehicleType type, Double amount) {
        this.country = country;
        this.voyageDate = voyageDate;
        this.type = type;
        this.amount = amount;
    }
}
