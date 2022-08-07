package com.example.airplaneandbusonlineticketapi.configurationservice.model;

import com.example.airplaneandbusonlineticketapi.configurationservice.model.enums.CurrencyType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "messages")
@Data
public class Message {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @JsonIgnore
    private String name;
    private String surname;
    @JsonIgnore
    @Column(name = "phone_number")
    private String toPhone;
    @JsonIgnore
    private String title;
    @JsonIgnore
    private String content;
    @JsonIgnore
    private Double amount;
    @JsonIgnore
    @Column(name = "currency_type")
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
}
