package com.example.airplaneandbusonlineticketapi.model;

import com.example.airplaneandbusonlineticketapi.model.enums.GenderType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "individual_user")
public class IndividualUser {
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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "individual_user_id", insertable = true)
    private List<Passenger> passengerList = new ArrayList<>(10);

}
