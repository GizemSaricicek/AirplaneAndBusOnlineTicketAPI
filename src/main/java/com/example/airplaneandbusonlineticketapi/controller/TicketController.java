package com.example.airplaneandbusonlineticketapi.controller;

import com.example.airplaneandbusonlineticketapi.dto.TicketDto;
import com.example.airplaneandbusonlineticketapi.model.Passenger;
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
    public List<TicketDto> createTickets(@PathVariable Integer userId, @PathVariable Integer voyageId, @RequestBody List<TicketDto> ticketDtos){
        return ticketService.createTickets(userId, voyageId, ticketDtos);
    }
    @GetMapping("/{userId}")
    public List<TicketDto> getTicketsByUserId(@PathVariable Integer userId){
        return ticketService.getTicketsByUserId(userId);
    }

    @GetMapping()
    public List<TicketDto> getAllTickets(){
        return ticketService.getAllTickets();
    }

}
