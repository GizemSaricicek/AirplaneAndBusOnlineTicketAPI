package com.example.ticketpaymentservice.service;

import com.example.ticketpaymentservice.dto.TicketDto;
import com.example.ticketpaymentservice.model.Ticket;
import com.example.ticketpaymentservice.model.enums.CurrencyType;
import com.example.ticketpaymentservice.model.enums.VehicleType;
import com.example.ticketpaymentservice.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> createPayments(Integer userId, Integer voyageId, CurrencyType currencyType, Double amount, VehicleType vehicleType, List<TicketDto> ticketDtos) {

        //return için
        List<Ticket> tickets = new ArrayList<>();

        for (int i = 0; i < ticketDtos.size(); i++) {

            Ticket ticket = new Ticket();
            log.info(ticketDtos.get(i).getName());

            ticket.setPaymentDate(LocalDateTime.now());
            ticket.setPaymentType(ticketDtos.get(i).getPaymentType());
            ticket.setAmount(amount);
            ticket.setVehicleType(vehicleType);
            ticket.setCurrencyType(currencyType);
            ticket.setUserId(userId);
            ticket.setVoyageId(voyageId);
            ticket.setName(ticketDtos.get(i).getName());
            ticket.setSurname(ticketDtos.get(i).getSurname());
            ticket.setEmail(ticketDtos.get(i).getEmail());
            ticket.setPhoneNumber(ticketDtos.get(i).getPhoneNumber());
            ticket.setAge(ticketDtos.get(i).getAge());
            ticket.setGender(ticketDtos.get(i).getGender());

            ticketRepository.save(ticket);
            tickets.add(ticket);
        }
        return tickets;
    }

    public List<Ticket> getTicketByUserId(Integer userId) {

        log.info(userId.toString());

        return ticketRepository.findByUserId(userId);
    }

    public List<Ticket> getAllTickets() {

        return ticketRepository.findAll();
    }

    public List<Ticket> getTicketByCurrencyType(CurrencyType currencyType) {

        log.info(currencyType.toString());

        return ticketRepository.findByCurrencyType(currencyType);
    }

    public List<Ticket> getTicketsByUserIdAndVoyageId(Integer userId, Integer voyageId) {

        log.info(userId.toString(), voyageId.toString());

        return ticketRepository.findByUserIdAndVoyageId(userId, voyageId);
    }

    public List<Ticket> getTicketsByVoyageId(Integer voyageId) {
        log.info(voyageId.toString());
        return ticketRepository.findByVoyageId(voyageId);
    }

    public List<Ticket> getTicketsByVehicleType(VehicleType vehicleType) {

        log.info(vehicleType.toString());

        return ticketRepository.findByVehicleType(vehicleType);
    }

    public Double getAllTicketsTotalAmount() {

        List<Ticket> allTickets = ticketRepository.findAll();
        Double sum = allTickets.stream().mapToDouble(amount -> amount.getAmount()).sum();
        return sum;
    }
}
