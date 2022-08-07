package com.example.adminservice.controller;

import com.example.adminservice.dto.TicketDto;
import com.example.adminservice.model.enums.CurrencyType;
import com.example.adminservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping()
    public List<TicketDto> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/totalAmount")
    public Double getTicketsTotalAmount() {
        return ticketService.getTicketsTotalAmount();
    }

    @GetMapping("/soldTicket")
    public Integer getSoldTicketsNumber() {
        return ticketService.getSoldTicketsNumber();
    }

    @GetMapping("/totalAmount/{currencyType}")
    public Double getTicketsTotalAmount(@PathVariable CurrencyType currencyType) {
        return ticketService.getTicketsTotalAmountByCurrencyType(currencyType);
    }
}
