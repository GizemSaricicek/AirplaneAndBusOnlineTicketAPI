package com.example.adminservice.service;

import com.example.adminservice.dto.AdminDto;
import com.example.adminservice.exception.OnlineTicketAppException;
import com.example.adminservice.model.Admin;
import com.example.adminservice.repository.AdminRepository;
import com.example.adminservice.security.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private Encryptor encryptor;

    public Admin loginAdmin(AdminDto adminDto) {
        String inputAdminPassword = encryptor.encryptGivenPassword(adminDto.getPassword());
        Admin foundAdmin = adminRepository.findByEmailAndPassword(adminDto.getEmail(), inputAdminPassword).orElseThrow(() -> new OnlineTicketAppException("Admin not found."));
        return foundAdmin;
    }

    public Admin createAdmin(AdminDto adminDto) {

        // Admin kaydı kontrolü.
        boolean isExists = adminRepository.findByEmail(adminDto.getEmail()).isPresent();

        if (isExists) {
            throw new OnlineTicketAppException("Admin already exists.");
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
