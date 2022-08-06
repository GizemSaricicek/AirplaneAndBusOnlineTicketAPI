package com.example.airplaneandbusonlineticketapi.model;

import com.example.airplaneandbusonlineticketapi.model.enums.CurrencyType;
import com.example.airplaneandbusonlineticketapi.model.enums.VehicleType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "voyage")
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String country;
    @Column
    String departure;
    @Column(name = "voyage_date")
    private LocalDateTime voyageDate;
    @Column
    private Double amount;
    private boolean status;
    @Column
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
    @Column
    @Enumerated(EnumType.STRING)
    private VehicleType type;


    public Voyage() {
    }

    public Integer getId() {
        return id;
    }

    public String getCountry() {
        return country;
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

    public Voyage(Integer id, String country, String departure, LocalDateTime voyageDate, Double amount, boolean status, CurrencyType currencyType, VehicleType type) {
        this.id = id;
        this.country = country;
        this.departure = departure;
        this.voyageDate = voyageDate;
        this.amount = amount;
        this.status = status;
        this.currencyType = currencyType;
        this.type = type;
    }

    //    public void setCurrencyType(CurrencyType currencyType) {
//        this.currencyType = currencyType;
//    }
    public Voyage(String country, LocalDateTime voyageDate, VehicleType type, Double amount) {
        this.country = country;
        this.voyageDate = voyageDate;
        this.type = type;
        this.amount = amount;
    }
//    public boolean isStatus() {
//        return status;
//    }
//
//    public void setStatus(boolean status) {
//        this.status = status;
//    }
//    public void setAmount(Double amount) {
//        this.amount = amount;
//    }
//    public void setType(VehicleType type) {
//        this.type = type;
//    }
//
//    public Voyage(Integer id, String country, LocalDateTime voyageDate, VehicleType type, Double amount, CurrencyType currencyType) {
//        this.id = id;
//        this.country = country;
//        this.voyageDate = voyageDate;
//        this.type = type;
//        this.amount = amount;
//        this.currencyType = currencyType;
//    }
//    public void setCountry(String country) {
//        this.country = country;
//    }
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public LocalDateTime getVoyageDate() {
//        return voyageDate;
//    }
//
//    public void setVoyageDate(LocalDateTime voyageDate) {
//        this.voyageDate = voyageDate;
//    }

}
