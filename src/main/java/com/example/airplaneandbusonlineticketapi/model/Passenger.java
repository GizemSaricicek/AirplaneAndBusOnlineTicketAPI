package com.example.airplaneandbusonlineticketapi.model;

import com.example.airplaneandbusonlineticketapi.model.enums.GenderType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String password;
    @Column
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    @Column
    private Integer age;
    @JsonIgnore
    @ManyToOne
    private IndividualUser individualUser;
    @JsonIgnore
    @ManyToOne
    private CorporateUser corporateUser;
}
