package com.example.ticketpaymentservice.controller;

import com.example.ticketpaymentservice.dto.TicketDto;
import com.example.ticketpaymentservice.model.Ticket;
import com.example.ticketpaymentservice.model.enums.CurrencyType;
import com.example.ticketpaymentservice.model.enums.VehicleType;
import com.example.ticketpaymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{userId}/{voyageId}/{currencyType}/{amount}/{vehicleType}")
    public List<Ticket> createPayments(@PathVariable Integer userId, @PathVariable Integer voyageId, @PathVariable CurrencyType currencyType, @PathVariable Double amount, @PathVariable VehicleType vehicleType, @RequestBody List<TicketDto> ticketDtos) {
        return paymentService.createPayments(userId, voyageId, currencyType, amount, vehicleType, ticketDtos);
    }

    @GetMapping("/{userId}")
    public List<Ticket> getPaymentsByUserId(@PathVariable Integer userId) {
        return paymentService.getTicketByUserId(userId);
    }

    @GetMapping
    public List<Ticket> getAllTickets() { //user'a göre ticket listelemek.
        return paymentService.getAllTickets();
    }

    @GetMapping("/tickets/totalAmount")
    public Double getAllTicketsTotalAmount() { //user'a göre ticket listelemek.
        return paymentService.getAllTicketsTotalAmount();
    }

    @GetMapping("/currencyType/{currencyType}")
    public List<Ticket> getTicketsByCurrencyType(@PathVariable CurrencyType currencyType) {
        return paymentService.getTicketByCurrencyType(currencyType);
    }

    @GetMapping("/tickets/{userId}/{voyageId}")
    List<Ticket> getTicketsByUserIdAndVoyageId(@PathVariable Integer userId, @PathVariable Integer voyageId) {
        return paymentService.getTicketsByUserIdAndVoyageId(userId, voyageId);
    }

    @GetMapping("/capacity/{voyageId}")
    List<Ticket> getTicketsByVoyageId(@PathVariable Integer voyageId) {
        return paymentService.getTicketsByVoyageId(voyageId);
    }
}
