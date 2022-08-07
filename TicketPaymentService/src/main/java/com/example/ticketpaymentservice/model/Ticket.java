package com.example.ticketpaymentservice.model;

import com.example.ticketpaymentservice.model.enums.CurrencyType;
import com.example.ticketpaymentservice.model.enums.GenderType;
import com.example.ticketpaymentservice.model.enums.PaymentType;
import com.example.ticketpaymentservice.model.enums.VehicleType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "voyage_id")
    private Integer voyageId;
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
    @Column
    private Double amount;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column
    private Integer age;
    @Column
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Column(name = "currency_type")
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setVoyageId(Integer voyageId) {
        this.voyageId = voyageId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getVoyageId() {
        return voyageId;
    }

    public Integer getUserId() {
        return userId;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public Integer getAge() {
        return age;
    }

    public GenderType getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

}
