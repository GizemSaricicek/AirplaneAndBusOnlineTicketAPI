package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.exception.OnlineTicketAppException;
import com.example.airplaneandbusonlineticketapi.model.Voyage;
import com.example.airplaneandbusonlineticketapi.model.enums.VehicleType;
import com.example.airplaneandbusonlineticketapi.repository.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VoyageService {

    @Autowired
    VoyageRepository voyageRepository;

    public List<Voyage> getCurrentVoyages() {
        List<Voyage> voyages = voyageRepository.findByStatus(true);
        if(voyages.isEmpty()){
            new OnlineTicketAppException("There is no voyage in the system.");
        }
        return voyages;
    }

    public List<Voyage> getVoyagesByCountry(String country) {
        List<Voyage> voyages = voyageRepository.findByCountry(country);
        if(voyages.isEmpty()){
            throw new OnlineTicketAppException("There is no voyage for this country.");
        }
        return voyages;
    }

    public List<Voyage> getVoyagesByVehicleType(VehicleType vehicleType) {
        List<Voyage> voyages = voyageRepository.findByType(vehicleType);
        if(voyages.isEmpty()){
            throw new OnlineTicketAppException("There is no voyage for this vehicle type.");
        }
        return voyages;
    }

    public List<Voyage> getVoyagesByDate(LocalDateTime date) {
        List<Voyage> voyages = voyageRepository.findByVoyageDate(date);
        if(voyages.isEmpty()) {
            throw new OnlineTicketAppException("There is no voyage for this date and time.");
        }
        return voyages;
    }
}
