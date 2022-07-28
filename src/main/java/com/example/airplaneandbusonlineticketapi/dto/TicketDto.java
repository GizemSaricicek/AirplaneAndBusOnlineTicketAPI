package com.example.airplaneandbusonlineticketapi.dto;

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
    private PassengerDto passengerDto;

    public TicketDto(Integer userId, Integer voyageId, CurrencyType currencyType, Double amount, PaymentType paymentType, PassengerDto passengerDto) {
        this.userId = userId;
        this.voyageId = voyageId;
        this.currencyType = currencyType;
        this.amount = amount;
        this.paymentType = paymentType;
        this.passengerDto = passengerDto;
    }
}
