package com.example.airplaneandbusonlineticketapi.dto;

import com.example.airplaneandbusonlineticketapi.model.enums.GenderType;
import com.example.airplaneandbusonlineticketapi.model.enums.PaymentType;
import com.example.airplaneandbusonlineticketapi.model.enums.CurrencyType;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class TicketDto {
    private Integer userId;
    private Integer voyageId;
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    private Integer age;

    public TicketDto(Integer userId, Integer voyageId, CurrencyType currencyType, Double amount, PaymentType paymentType, String name, String surname, String email, String phoneNumber, GenderType gender, Integer age) {
        this.userId = userId;
        this.voyageId = voyageId;
        this.currencyType = currencyType;
        this.amount = amount;
        this.paymentType = paymentType;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.age = age;
    }

    public TicketDto(int userId, int voyageId, CurrencyType tl, double amount) {
        this.userId = userId;
        this.voyageId = voyageId;
        this.currencyType = currencyType;
        this.amount = amount;
    }
}
