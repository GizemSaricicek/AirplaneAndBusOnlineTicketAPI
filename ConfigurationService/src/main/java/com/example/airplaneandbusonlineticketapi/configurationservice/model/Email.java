package com.example.airplaneandbusonlineticketapi.configurationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "emails")
@Data
public class Email {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column
    @JsonIgnore
    private String toEmail;
    @Column
    @JsonIgnore
    private String title;
    @Column(name = "email_message")
    @JsonIgnore
    private String emailMessage;
}
