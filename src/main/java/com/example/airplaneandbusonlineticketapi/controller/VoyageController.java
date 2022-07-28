package com.example.airplaneandbusonlineticketapi.controller;

import com.example.airplaneandbusonlineticketapi.dto.VoyageDto;
import com.example.airplaneandbusonlineticketapi.model.Voyage;
import com.example.airplaneandbusonlineticketapi.model.enums.VehicleType;
import com.example.airplaneandbusonlineticketapi.service.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/voyages")
public class VoyageController {

    @Autowired
    VoyageService voyageService;

    @PostMapping("/{adminId}/create")
    public Voyage createVoyage(@PathVariable Integer adminId, @RequestBody VoyageDto voyageDto) {
        return voyageService.createVoyage(adminId, voyageDto);
    }

    @DeleteMapping("/{adminId}/delete/{voyageId}")
    public String deleteVoyage(@PathVariable Integer adminId, @PathVariable Integer voyageId) {
        return voyageService.deleteVoyageById(adminId, voyageId);
    }

    @GetMapping
    public List<Voyage> getAllVoyages() {
        return voyageService.getAllVoyages();
    }

    @GetMapping("/country/{country}")
    public List<Voyage> getVoyagesByCountry(@PathVariable String country) {
        return voyageService.getVoyagesByCountry(country);
    }

    @GetMapping("/vehicle/{vehicle}")
    public List<Voyage> getVoyagesByVehicleType(@PathVariable("vehicle") VehicleType vehicleType) {
        return voyageService.getVoyagesByVehicleType(vehicleType);
    }

    @GetMapping("/date/{date}")
    public List<Voyage> getVoyagesByDate(/*@RequestParam("date")*/
            @PathVariable("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime voyagedate) {
        return voyageService.getVoyagesByDate(voyagedate);
    }
}
