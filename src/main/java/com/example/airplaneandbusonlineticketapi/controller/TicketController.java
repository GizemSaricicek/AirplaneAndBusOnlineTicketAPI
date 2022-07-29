package com.example.airplaneandbusonlineticketapi.controller;

import com.example.airplaneandbusonlineticketapi.dto.TicketDto;
import com.example.airplaneandbusonlineticketapi.model.enums.CurrencyType;
import com.example.airplaneandbusonlineticketapi.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tickets")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/{userId}/{voyageId}")
    public TicketDto createTicket(@PathVariable Integer userId, @PathVariable Integer voyageId, @RequestBody TicketDto ticketDto){
        return ticketService.createTicket(userId, voyageId, ticketDto);
    }

    @GetMapping("/{userId}")
    public List<TicketDto> getTicketsByUserId(@PathVariable Integer userId){
        return ticketService.getTicketsByUserId(userId);
    }

    @GetMapping()
    public List<TicketDto> getAllTickets(){
        return ticketService.getAllTickets();
    }

    @GetMapping("/totalAmount")
    public Double getTicketsTotalAmount(){
        return ticketService.getTicketsTotalAmount();
    }

    @GetMapping("/soldTicket")
    public Integer getSoldTicketsNumber(){
        return ticketService.getSoldTicketsNumber();
    }

    @GetMapping("/totalAmount/{currencyType}")
    public Double getTicketsTotalAmount(@PathVariable CurrencyType currencyType){
        return ticketService.getTicketsTotalAmountByCurrencyType(currencyType);
    }
}
