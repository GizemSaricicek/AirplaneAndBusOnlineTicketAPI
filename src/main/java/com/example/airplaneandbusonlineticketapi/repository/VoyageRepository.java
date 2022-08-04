package com.example.airplaneandbusonlineticketapi.repository;

import com.example.airplaneandbusonlineticketapi.model.Voyage;
import com.example.airplaneandbusonlineticketapi.model.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VoyageRepository extends JpaRepository<Voyage, Integer> {
    List<Voyage> findByCountry(String country);

    List<Voyage> findByType(VehicleType vehicleType);

    List<Voyage> findByVoyageDate(LocalDateTime date);

    List<Voyage> findByStatus(boolean b);
}
