package com.example.adminservice.dto;

import com.example.adminservice.model.enums.CurrencyType;
import com.example.adminservice.model.enums.GenderType;
import com.example.adminservice.model.enums.PaymentType;
import com.example.adminservice.model.enums.VehicleType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class TicketDto {
    private Double amount;
    private Integer userId;
    private Integer voyageId;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private GenderType gender;
    private VehicleType vehicleType;

    public Double getAmount() {
        return amount;
    }

    public TicketDto(int userId, int voyageId, CurrencyType currencyType, double amount, VehicleType vehicleType) {
        this.userId = userId;
        this.voyageId = voyageId;
        this.amount = amount;
        this.currencyType = currencyType;
        this.vehicleType = vehicleType;
    }

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

    public TicketDto(int userId, int voyageId, CurrencyType currencyType, double amount) {
        this.userId = userId;
        this.voyageId = voyageId;
        this.currencyType = currencyType;
        this.amount = amount;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public TicketDto() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVoyageId() {
        return voyageId;
    }

    public void setVoyageId(Integer voyageId) {
        this.voyageId = voyageId;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }
}
