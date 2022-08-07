package com.example.adminservice.client;

import com.example.adminservice.dto.TicketDto;
import com.example.adminservice.model.enums.CurrencyType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "${payment.url}", value = "airplane-bus-ticket-payment-service")
public interface PaymentClient {

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
