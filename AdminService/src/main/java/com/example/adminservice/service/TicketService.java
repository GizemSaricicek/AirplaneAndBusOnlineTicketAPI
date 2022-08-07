package com.example.adminservice.service;

import com.example.adminservice.client.PaymentClient;
import com.example.adminservice.dto.TicketDto;
import com.example.adminservice.model.enums.CurrencyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private PaymentClient paymentClient;

    public List<TicketDto> getAllTickets() {

        return paymentClient.getAllTickets();
    }

    public Double getTicketsTotalAmount() {

        List<TicketDto> allTickets = paymentClient.getAllTickets();

        Double sum = allTickets.stream().mapToDouble(amount -> amount.getAmount()).sum();

        return sum;
    }

    public Double getTicketsTotalAmountByCurrencyType(CurrencyType currencyType) {

        List<TicketDto> allTicketsByCurrencyType = paymentClient.getTicketsByCurrencyType(currencyType);

        double sum = allTicketsByCurrencyType.stream().mapToDouble(amount -> amount.getAmount()).sum();
        return sum;
    }

    public Integer getSoldTicketsNumber() {

        return paymentClient.getAllTickets().size();
    }
}
