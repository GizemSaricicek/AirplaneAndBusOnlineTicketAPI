package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.dto.VoyageDto;
import com.example.airplaneandbusonlineticketapi.exception.NoAdminException;
import com.example.airplaneandbusonlineticketapi.model.Voyage;
import com.example.airplaneandbusonlineticketapi.repository.AdminRepository;
import com.example.airplaneandbusonlineticketapi.repository.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoyageService {

    private final String deleteMessage = "Voyage is deleted successfully.";
    @Autowired
    VoyageRepository voyageRepository;

    @Autowired
    AdminRepository adminRepository;

    public Voyage createVoyage(Integer adminId, VoyageDto voyageDto) {

        //admin olup olmaması kontrol ediliyor. Yetkisiz bir id işlem yapmamalı.
        adminRepository.findById(adminId).orElseThrow(() -> new NoAdminException());

        Voyage voyage = new Voyage();
        voyage.setCountry(voyageDto.getCountry());
        voyage.setVoyageDate(voyageDto.getVoyageDate());
        voyage.setMoney(voyageDto.getMoney());
        voyage.setType(voyageDto.getType());

        return voyageRepository.save(voyage);
    }

    public String deleteVoyageById(Integer adminId, Integer voyageId) {

        //admin olup olmaması kontrol ediliyor. Yetkisiz bir id işlem yapmamalı.
        adminRepository.findById(adminId).orElseThrow(() -> new NoAdminException());
        voyageRepository.deleteById(voyageId);

        return deleteMessage;
    }

    public List<Voyage> getAllVoyages() {
        return voyageRepository.findAll();
    }
}
