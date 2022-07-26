package com.example.airplaneandbusonlineticketapi.repository;

import com.example.airplaneandbusonlineticketapi.dto.AdminDto;
import com.example.airplaneandbusonlineticketapi.dto.IndividualUserDto;
import com.example.airplaneandbusonlineticketapi.model.IndividualUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndividualUserRepository extends JpaRepository<IndividualUser, Integer> {
    Optional<IndividualUser> findByEmail(String email);
}
