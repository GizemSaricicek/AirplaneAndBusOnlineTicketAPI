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

    public VoyageDto() {
    }

    public VoyageDto(String country, LocalDateTime voyageDate, VehicleType type, Double amount) {
        this.country = country;
        this.voyageDate = voyageDate;
        this.type = type;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getVoyageDate() {
        return voyageDate;
    }

    public void setVoyageDate(LocalDateTime voyageDate) {
        this.voyageDate = voyageDate;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }
}
