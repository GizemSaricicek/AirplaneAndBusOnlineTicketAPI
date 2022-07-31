package com.example.airplaneandbusonlineticketapi.model;

import com.example.airplaneandbusonlineticketapi.model.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String fullName;
    @Column
    private String password;
    @Column(name="user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Column
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", insertable = true)
//    private List<Passenger> passengerList = new ArrayList<>(10);

    public User(Integer id, String fullName, String password, UserType userType, String email, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.password = password;
        this.userType = userType;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User() {

    }
}
