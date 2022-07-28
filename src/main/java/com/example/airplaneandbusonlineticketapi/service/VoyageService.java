package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.dto.VoyageDto;
import com.example.airplaneandbusonlineticketapi.exception.NoAdminException;
import com.example.airplaneandbusonlineticketapi.exception.VoyagenNotFoundException;
import com.example.airplaneandbusonlineticketapi.model.Voyage;
import com.example.airplaneandbusonlineticketapi.model.enums.VehicleType;
import com.example.airplaneandbusonlineticketapi.repository.AdminRepository;
import com.example.airplaneandbusonlineticketapi.repository.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        voyage.setAmount(voyageDto.getAmount());
        voyage.setType(voyageDto.getType());
        voyage.setCurrencyType(voyageDto.getCurrencyType());

        return voyageRepository.save(voyage);
    }

    public String deleteVoyageById(Integer adminId, Integer voyageId) {

        //admin olup olmaması kontrol ediliyor. Yetkisiz bir id işlem yapmamalı.
        adminRepository.findById(adminId).orElseThrow(() -> new NoAdminException());

        //voyage olup olmaması kontrol ediliyor. Olmayan bir voyage silinemez.
        voyageRepository.findById(voyageId).orElseThrow(() -> new VoyagenNotFoundException());
        voyageRepository.deleteById(voyageId);

        return deleteMessage;
    }

    public List<Voyage> getAllVoyages() {
        return voyageRepository.findAll();
    }

    public List<Voyage> getVoyagesByCountry(String country) {
        return voyageRepository.findByCountry(country);
    }

    public List<Voyage> getVoyagesByVehicleType(VehicleType vehicleType) {
        return voyageRepository.findByType(vehicleType);
    }

    public List<Voyage> getVoyagesByDate(LocalDateTime date) {
        return voyageRepository.findByVoyageDate(date);

    }
}
