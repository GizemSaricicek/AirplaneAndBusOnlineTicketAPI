package com.example.airplaneandbusonlineticketapi.repository;

import com.example.airplaneandbusonlineticketapi.model.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoyageRepository extends JpaRepository<Voyage, Integer> {
}
