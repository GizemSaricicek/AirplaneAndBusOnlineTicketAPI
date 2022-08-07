package com.example.adminservice.model;

import com.example.adminservice.model.enums.CurrencyType;
import com.example.adminservice.model.enums.VehicleType;
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
    private String departure; //kalkış şehri
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
    private boolean status;

    public Voyage(Integer id, String country, LocalDateTime date, VehicleType vehicleType, double amount) {

        this.id = id;
        this.country = country;
        this.voyageDate = date;
        this.type = vehicleType;
        this.amount = amount;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public Voyage() {
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setVoyageDate(LocalDateTime voyageDate) {
        this.voyageDate = voyageDate;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
