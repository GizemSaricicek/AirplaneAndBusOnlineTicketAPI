package com.example.adminservice.repository;

import com.example.adminservice.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByEmail(String email);

    Optional<Admin> findByEmailAndPassword(String email, String password);

    Optional<Admin> findByPassword(String encryptGivenPassword);
}
