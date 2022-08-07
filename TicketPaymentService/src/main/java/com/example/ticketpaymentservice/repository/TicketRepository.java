package com.example.ticketpaymentservice.repository;

import com.example.ticketpaymentservice.model.Ticket;
import com.example.ticketpaymentservice.model.enums.CurrencyType;
import com.example.ticketpaymentservice.model.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByUserId(Integer userId);

    List<Ticket> findByCurrencyType(CurrencyType currencyType);

    List<Ticket> findByUserIdAndVoyageId(Integer userId, Integer voyageId);

    List<Ticket> findByVoyageId(Integer voyageId);

    List<Ticket> findByVehicleType(VehicleType vehicleType);
}
