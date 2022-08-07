package com.example.adminservice.service;

import com.example.adminservice.dto.VoyageDto;
import com.example.adminservice.exception.OnlineTicketAppException;
import com.example.adminservice.model.Voyage;
import com.example.adminservice.model.enums.VehicleType;
import com.example.adminservice.repository.AdminRepository;
import com.example.adminservice.repository.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VoyageService {

    @Autowired
    private VoyageRepository voyageRepository;

    @Autowired
    private AdminRepository adminRepository;

    public Voyage createVoyage(Integer adminId, VoyageDto voyageDto) {

        //admin olup olmaması kontrol ediliyor. Yetkisiz bir id işlem yapmamalı.
        adminRepository.findById(adminId).orElseThrow(() -> new OnlineTicketAppException("Cannot add voyage. There is no admin with this information in the system."));

        Voyage voyage = new Voyage();
        voyage.setCountry(voyageDto.getCountry());
        voyage.setVoyageDate(voyageDto.getVoyageDate());
        voyage.setAmount(voyageDto.getAmount());
        voyage.setType(voyageDto.getType());
        voyage.setCurrencyType(voyageDto.getCurrencyType());
        voyage.setStatus(true);
        voyage.setDeparture(voyageDto.getDeparture());

        return voyageRepository.save(voyage);
    }

    public Optional<Voyage> deleteVoyageById(Integer adminId, Integer voyageId) { //STATUS DEĞİŞİKLİĞİ YAP DELETE ETME

        //admin olup olmaması kontrol ediliyor. Yetkisiz bir id işlem yapmamalı.
        adminRepository.findById(adminId).orElseThrow(() -> new OnlineTicketAppException("There is no admin with this information in the system."));

        //voyage olup olmaması kontrol ediliyor. Olmayan bir voyage silinemez.
        Voyage foundVoyage = voyageRepository.findById(voyageId).orElseThrow(() -> new OnlineTicketAppException("Voyage not found."));

        //voyage statusu false olarak değiştiriliyor.
        foundVoyage.setStatus(false);
        voyageRepository.save(foundVoyage);

        return Optional.of(foundVoyage);
    }

    public List<Voyage> getAllVoyages() {

        return voyageRepository.findByStatus(true);
    }

    public List<Voyage> getVoyagesByCountry(String country) {

        return voyageRepository.findByCountryAndStatus(country, true);
    }

    public List<Voyage> getVoyagesByVehicleType(VehicleType vehicleType) {

        return voyageRepository.findByTypeAndStatus(vehicleType, true);
    }

    public List<Voyage> getVoyagesByDate(LocalDateTime date) {

        return voyageRepository.findByVoyageDateAndStatus(date, true);
    }

    public Optional<Voyage> getVoyageById(Integer id) {

        return voyageRepository.findById(id);
    }
}
