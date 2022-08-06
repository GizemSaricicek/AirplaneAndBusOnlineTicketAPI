package com.example.airplaneandbusonlineticketapi.client;

import com.example.airplaneandbusonlineticketapi.dto.VoyageDto;
import com.example.airplaneandbusonlineticketapi.model.Voyage;
import com.example.airplaneandbusonlineticketapi.model.enums.VehicleType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@FeignClient(url = "${admin.url}", value = "airplane-bus-ticket-admin-service")

public interface AdminClient {
    @PostMapping("/voyages/{adminId}/create")
    Voyage createVoyage(@PathVariable Integer adminId, @RequestBody VoyageDto voyageDto);

    @PutMapping("/voyages/{adminId}/delete/{voyageId}")
    Optional<Voyage> deleteVoyage(@PathVariable Integer adminId, @PathVariable Integer voyageId);

    @GetMapping("/voyages")
    List<Voyage> getAllVoyages();

    @GetMapping("/voyages/id/{voyageId}")
    Optional<Voyage> getVoyageById(@PathVariable Integer voyageId);

    @GetMapping("/voyages/country/{country}")
    List<Voyage> getVoyagesByCountry(@PathVariable String country);

    @GetMapping("/voyages/vehicle/{vehicle}")
    List<Voyage> getVoyagesByVehicleType(@PathVariable("vehicle") VehicleType vehicleType);

    @GetMapping("/voyages/date/{date}")
    List<Voyage> getVoyagesByDate(
            @PathVariable("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime voyagedate);
}
