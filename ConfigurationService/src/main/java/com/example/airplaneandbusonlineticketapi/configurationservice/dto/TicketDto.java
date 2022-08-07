package com.example.airplaneandbusonlineticketapi.configurationservice.dto;


import com.example.airplaneandbusonlineticketapi.configurationservice.model.enums.CurrencyType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class TicketDto {
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
    private Double amount;
    private String name;
    private String surname;
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public Double getAmount() {
        return amount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
