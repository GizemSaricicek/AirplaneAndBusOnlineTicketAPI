package com.example.ticketpaymentservice.dto;

import com.example.ticketpaymentservice.model.enums.CurrencyType;
import com.example.ticketpaymentservice.model.enums.GenderType;
import com.example.ticketpaymentservice.model.enums.PaymentType;
import com.example.ticketpaymentservice.model.enums.VehicleType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class TicketDto {
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    private Integer age;
    private Integer userId;
    private Integer voyageId;
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
    private double amount;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    public TicketDto() {
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public TicketDto(Integer userId, Integer voyageId, CurrencyType currencyType, double amount, VehicleType vehicleType) {
        this.userId = userId;
        this.voyageId = voyageId;
        this.currencyType = currencyType;
        this.amount = amount;
        this.vehicleType = vehicleType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setVoyageId(Integer voyageId) {
        this.voyageId = voyageId;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public GenderType getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
