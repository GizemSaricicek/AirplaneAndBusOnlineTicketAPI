package com.example.airplaneandbusonlineticketapi.model;

import com.example.airplaneandbusonlineticketapi.model.enums.VehicleType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "voyage")
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column
    private String country;
    @Column(name = "voyage_date")
    private LocalDateTime voyageDate;
    @Column
    @Enumerated(EnumType.STRING)
    private VehicleType type;
    @Column
    private Double money;
}
