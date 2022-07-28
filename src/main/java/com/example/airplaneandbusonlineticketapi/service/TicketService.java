package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.client.PaymentClient;
import com.example.airplaneandbusonlineticketapi.dto.PassengerDto;
import com.example.airplaneandbusonlineticketapi.dto.TicketDto;
import com.example.airplaneandbusonlineticketapi.exception.CannotBuyTicketException;
import com.example.airplaneandbusonlineticketapi.exception.UserNotFoundException;
import com.example.airplaneandbusonlineticketapi.exception.VoyagenNotFoundException;
import com.example.airplaneandbusonlineticketapi.model.User;
import com.example.airplaneandbusonlineticketapi.model.Voyage;
import com.example.airplaneandbusonlineticketapi.model.enums.CurrencyType;
import com.example.airplaneandbusonlineticketapi.model.enums.UserType;
import com.example.airplaneandbusonlineticketapi.repository.UserRepository;
import com.example.airplaneandbusonlineticketapi.repository.VoyageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TicketService {
    @Autowired
    PaymentClient paymentClient;

    @Autowired
    VoyageRepository voyageRepository;
    @Autowired
    UserRepository userRepository;

    public TicketDto createTicket(Integer userId, Integer voyageId, TicketDto ticketDto, PassengerDto passengerDto) {

        final Integer MAX_INDIVIDUAL_TICKET = 5;
        final Integer MAX_CORPORATE_TICKET = 20;

        Voyage foundVoyage = voyageRepository.findById(voyageId).orElseThrow(() -> new VoyagenNotFoundException());
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());

        List<TicketDto> ticketsNumber = paymentClient.getTicketsByUserIdAndVoyageId(userId, voyageId); // Integer olarak alabilirsin sonra sum() demek yerine.

        if((foundUser.getUserType().equals(UserType.INDIVIDUAL) && ticketsNumber.size() > MAX_INDIVIDUAL_TICKET-1) || (foundUser.getUserType().equals(UserType.CORPORATE) && ticketsNumber.size() > MAX_CORPORATE_TICKET-1) ){
            throw new CannotBuyTicketException();
        }
        TicketDto ticketDtoTemp = new TicketDto(userId, voyageId, foundVoyage.getCurrencyType(), foundVoyage.getAmount(), ticketDto.getPaymentType(), passengerDto);
        TicketDto ticketPayment = paymentClient.createPayment(ticketDtoTemp);
        log.info(ticketPayment.toString());
        log.info(passengerDto.toString());
        return ticketDtoTemp;
    }

    public List<TicketDto> getTicketsByUserId(Integer userId) {

        User foundUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());

        List<TicketDto> tickets = paymentClient.getTicketsByUserId(userId);

        return tickets;
    }

    public List<TicketDto> getAllTickets() {
        return paymentClient.getAllTickets();
    }

    public Double getTicketsTotalAmount() {

        List<TicketDto> allTickets = paymentClient.getAllTickets();
        double sum = allTickets.stream().mapToDouble(amount -> amount.getAmount()).sum();
        return sum;
    }

    public Double getTicketsTotalAmountByCurrencyType(CurrencyType currencyType) {
        List<TicketDto> allTickets = paymentClient.getTicketsByCurrencyType(currencyType);
        double sum = allTickets.stream().mapToDouble(amount -> amount.getAmount()).sum();
        return sum;
    }

    public Integer getSoldTicketsNumber() {
        return paymentClient.getAllTickets().size();
    }
}
