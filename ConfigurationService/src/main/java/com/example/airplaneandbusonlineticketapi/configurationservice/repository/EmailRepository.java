package com.example.airplaneandbusonlineticketapi.configurationservice.repository;

import com.example.airplaneandbusonlineticketapi.configurationservice.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {
}


