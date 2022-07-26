package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.dto.AdminDto;
import com.example.airplaneandbusonlineticketapi.exception.AdminAlreadyExistsException;
import com.example.airplaneandbusonlineticketapi.model.Admin;
import com.example.airplaneandbusonlineticketapi.repository.AdminRepository;
import com.example.airplaneandbusonlineticketapi.security.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    Encryptor encryptor;

    public Admin createAdmin(AdminDto adminDto) {

        //Admin kaydı kontrolü. Admin yoksa oluşturuluyor.
        boolean isExists = adminRepository.findByEmail(adminDto.getEmail()).isPresent();

        if (isExists) {
            throw new AdminAlreadyExistsException();
        }

        Admin admin = new Admin();
        admin.setName(adminDto.getName());
        admin.setSurname(adminDto.getSurname());
        admin.setPassword(encryptor.encryptGivenPassword(adminDto.getPassword()));
        admin.setEmail(adminDto.getEmail());
        adminRepository.save(admin);
        return admin;
    }
}
