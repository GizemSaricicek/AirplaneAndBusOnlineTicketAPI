package com.example.airplaneandbusonlineticketapi.repository;

import com.example.airplaneandbusonlineticketapi.model.CorporateUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CorporateUserRepository extends JpaRepository<CorporateUser, Integer> {
    Optional<CorporateUser> findByEmail(String email);
    Optional<CorporateUser> findByEmailAndPassword(String email, String password);
}
