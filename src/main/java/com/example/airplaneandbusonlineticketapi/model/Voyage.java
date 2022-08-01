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
    @Column(name = "voyage_date")
    private LocalDateTime voyageDate;
    @Column
    @Enumerated(EnumType.STRING)
    private VehicleType type;
    @Column
    private Double amount;
    @Column
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    public Voyage(String country, LocalDateTime voyageDate, VehicleType type, Double amount) {
        this.country = country;
        this.voyageDate = voyageDate;
        this.type = type;
        this.amount = amount;
    }

    public Voyage(Integer id, String country, LocalDateTime voyageDate, VehicleType type, Double amount, CurrencyType currencyType) {
        this.id = id;
        this.country = country;
        this.voyageDate = voyageDate;
        this.type = type;
        this.amount = amount;
        this.currencyType = currencyType;
    }

    public Voyage() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
