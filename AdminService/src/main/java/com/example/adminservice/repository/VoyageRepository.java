package com.example.adminservice.repository;

import com.example.adminservice.model.Voyage;
import com.example.adminservice.model.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VoyageRepository extends JpaRepository<Voyage, Integer> {

    Optional<Voyage> findById(Integer id);

    List<Voyage> findByCountryAndStatus(String country, boolean b);

    List<Voyage> findByTypeAndStatus(VehicleType vehicleType, boolean b);

    List<Voyage> findByVoyageDateAndStatus(LocalDateTime date, boolean b);

    List<Voyage> findByStatus(boolean b);
}
