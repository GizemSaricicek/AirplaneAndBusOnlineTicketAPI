package com.example.adminservice.controller;

import com.example.adminservice.dto.VoyageDto;
import com.example.adminservice.model.Voyage;
import com.example.adminservice.model.enums.VehicleType;
import com.example.adminservice.service.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/voyages")
public class VoyageController {

    @Autowired
    private VoyageService voyageService;

    @PostMapping("/{adminId}/create")
    public Voyage createVoyage(@PathVariable Integer adminId, @RequestBody VoyageDto voyageDto) {
        return voyageService.createVoyage(adminId, voyageDto);
    }

    @PutMapping("/{adminId}/delete/{voyageId}")
    public Optional<Voyage> deleteVoyage(@PathVariable Integer adminId, @PathVariable Integer voyageId) {
        return voyageService.deleteVoyageById(adminId, voyageId);
    }

    @GetMapping
    public List<Voyage> getAllVoyages() {
        return voyageService.getAllVoyages();
    }

    @GetMapping("/id/{voyageId}")
    public Optional<Voyage> getVoyageById(@PathVariable Integer voyageId) {
        return voyageService.getVoyageById(voyageId);
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
    public List<Voyage> getVoyagesByDate(
            @PathVariable("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime voyagedate) {
        return voyageService.getVoyagesByDate(voyagedate);
    }
}
