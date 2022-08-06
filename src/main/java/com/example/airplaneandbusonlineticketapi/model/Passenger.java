package com.example.airplaneandbusonlineticketapi.model;

import com.example.airplaneandbusonlineticketapi.model.enums.GenderType;

import javax.persistence.*;

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
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column
    private Integer age;
    @Column(name = "voyage_id")
    private Integer voyageId;
    @Column
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    @ManyToOne
    private User user;

    public Passenger(String name, String surname, String email, String phoneNumber, GenderType genderType, Integer age, Integer voyageId) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.voyageId = voyageId;
        this.gender = genderType;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVoyageId(Integer voyageId) {
        this.voyageId = voyageId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Passenger() {
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Passenger(Integer id, String name, String surname, String email, String phoneNumber, Integer age, Integer voyageId, GenderType gender, User user) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.voyageId = voyageId;
        this.gender = gender;
        this.user = user;
    }

    //    public Integer getVoyageId() {
//        return voyageId;
//    }
//    public Integer getId() {
//        return id;
//    }
//    public Integer getAge() {
//        return age;
//    }
//    public void setId(Integer id) {
//        this.id = id;
//    }
//    public GenderType getGender() {
//        return gender;
//    }
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//    public String getSurname() {
//        return surname;
//    }

}
