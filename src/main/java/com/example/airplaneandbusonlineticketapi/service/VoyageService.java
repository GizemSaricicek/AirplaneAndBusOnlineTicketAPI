package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.client.AdminClient;
import com.example.airplaneandbusonlineticketapi.exception.OnlineTicketAppException;
import com.example.airplaneandbusonlineticketapi.model.Voyage;
import com.example.airplaneandbusonlineticketapi.model.enums.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoyageService {

    @Autowired
    private AdminClient adminClient;

    public List<Voyage> getCurrentVoyages() {

        List<Voyage> voyages = adminClient.getAllVoyages();

        if (voyages.isEmpty()) {
            new OnlineTicketAppException("There is no voyage in the system.");
        }
        return voyages;
    }

    public List<Voyage> getVoyagesByCountry(String country) {

        List<Voyage> voyagesByCountry = adminClient.getVoyagesByCountry(country);

        if (voyagesByCountry.isEmpty()) {
            throw new OnlineTicketAppException("There is no voyage for this country.");
        }
        return voyagesByCountry;
    }

    public List<Voyage> getVoyagesByVehicleType(VehicleType vehicleType) {

        List<Voyage> voyagesByVehicleType = adminClient.getVoyagesByVehicleType(vehicleType);

        if (voyagesByVehicleType.isEmpty()) {
            throw new OnlineTicketAppException("There is no voyage for this vehicle type.");
        }
        return voyagesByVehicleType;
    }

    public List<Voyage> getVoyagesByDate(LocalDateTime date) {

        List<Voyage> voyagesByDate = adminClient.getVoyagesByDate(date);

        if (voyagesByDate.isEmpty()) {
            throw new OnlineTicketAppException("There is no voyage for this date and time.");
        }
        return voyagesByDate;
    }
}
