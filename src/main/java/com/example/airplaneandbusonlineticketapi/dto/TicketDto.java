package com.example.airplaneandbusonlineticketapi.dto;

import com.example.airplaneandbusonlineticketapi.model.enums.GenderType;
import com.example.airplaneandbusonlineticketapi.model.enums.PaymentType;
import com.example.airplaneandbusonlineticketapi.model.enums.CurrencyType;
import com.example.airplaneandbusonlineticketapi.model.enums.VehicleType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class TicketDto {
    private Integer userId;
    private Integer voyageId;
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
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
//    private String country;
//    private String departure;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    public TicketDto(Integer userId, Integer voyageId, Double amount, PaymentType paymentType, String name, String surname, String email, String phoneNumber, GenderType gender, Integer age) {
        this.userId = userId;
        this.voyageId = voyageId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.age = age;
    }

    public TicketDto(int userId, int voyageId, CurrencyType currencyType, double amount, VehicleType vehicleType) {
        this.userId = userId;
        this.voyageId = voyageId;
        this.amount = amount;
        this.currencyType = currencyType;
        this.vehicleType = vehicleType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getUserId() {
        return userId;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
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

    public Double getAmount() {
        return amount;
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

//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getDeparture() {
//        return departure;
//    }
//
//    public void setDeparture(String departure) {
//        this.departure = departure;
//    }

    public TicketDto() {
    }
}
