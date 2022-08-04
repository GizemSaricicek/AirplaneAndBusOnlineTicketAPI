package com.example.airplaneandbusonlineticketapi.client;

import com.example.airplaneandbusonlineticketapi.dto.TicketDto;
import com.example.airplaneandbusonlineticketapi.model.enums.CurrencyType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(url = "${payment.url}", value = "airplane-bus-ticket-payment-service")
public interface PaymentClient {

    @PostMapping(value = "/payments/{userId}/{voyageId}/{currencyType}/{amount}")
    List<TicketDto> createPayments(@PathVariable Integer userId, @PathVariable Integer voyageId, @PathVariable CurrencyType currencyType, @PathVariable Double amount, @RequestBody List<TicketDto> ticketDtos);

    @GetMapping("/payments/{userId}")
    List<TicketDto> getTicketsByUserId(@PathVariable Integer userId);

    @GetMapping("/payments")
    List<TicketDto> getAllTickets();
    @GetMapping("/payments/tickets/totalAmount")
    Double getTicketsTotalAmount();
    @GetMapping("/payments/currencyType/{currencyType}")
    List<TicketDto> getTicketsByCurrencyType(@PathVariable CurrencyType currencyType);
    @GetMapping("payments/tickets/{userId}/{voyageId}")
    List<TicketDto> getTicketsByUserIdAndVoyageId(@PathVariable Integer userId, @PathVariable Integer voyageId);
    @GetMapping("payments/capacity/{voyageId}")
    List<TicketDto> getTicketsByVehicleType(@PathVariable Integer voyageId);
}
