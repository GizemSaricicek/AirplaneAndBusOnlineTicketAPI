package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.client.PaymentClient;
import com.example.airplaneandbusonlineticketapi.dto.TicketDto;
import com.example.airplaneandbusonlineticketapi.exception.CannotBuyTicketException;
import com.example.airplaneandbusonlineticketapi.exception.UserNotFoundException;
import com.example.airplaneandbusonlineticketapi.exception.VoyagenNotFoundException;
import com.example.airplaneandbusonlineticketapi.model.User;
import com.example.airplaneandbusonlineticketapi.model.Voyage;
import com.example.airplaneandbusonlineticketapi.model.enums.CurrencyType;
import com.example.airplaneandbusonlineticketapi.model.enums.UserType;
import com.example.airplaneandbusonlineticketapi.model.enums.VehicleType;
import com.example.airplaneandbusonlineticketapi.repository.UserRepository;
import com.example.airplaneandbusonlineticketapi.repository.VoyageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TicketService {

    final Integer MAX_INDIVIDUAL_TICKET = 5;
    final Integer MAX_CORPORATE_TICKET = 20;
    final Integer MAX_AIRPLANE_PASSENGER = 189;
    final Integer MAX_BUS_PASSENGER = 45;

    @Autowired
    PaymentClient paymentClient;
    @Autowired
    VoyageRepository voyageRepository;
    @Autowired
    UserRepository userRepository;

    public TicketDto createTicket(Integer userId, Integer voyageId, TicketDto ticketDto) {

        Voyage foundVoyage = voyageRepository.findById(voyageId).orElseThrow(() -> new VoyagenNotFoundException());
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());

        List<TicketDto> passengerNumber = paymentClient.getTicketsByVehicleType(voyageId);
        log.info(passengerNumber.toString());

        if ((foundVoyage.getType().equals(VehicleType.AIRPLANE) && (passengerNumber.size() > (MAX_AIRPLANE_PASSENGER - 1))) || (foundVoyage.getType().equals(VehicleType.BUS) && (passengerNumber.size() > (MAX_BUS_PASSENGER - 1)))) {
            throw new CannotBuyTicketException();
        }

        List<TicketDto> ticketsNumber = paymentClient.getTicketsByUserIdAndVoyageId(userId, voyageId); // Integer olarak alabilirsin sonra sum() demek yerine.

        if ((foundUser.getUserType().equals(UserType.INDIVIDUAL) && ticketsNumber.size() > MAX_INDIVIDUAL_TICKET - 1) || (foundUser.getUserType().equals(UserType.CORPORATE) && ticketsNumber.size() > MAX_CORPORATE_TICKET - 1)) {
            throw new CannotBuyTicketException();
        }
        TicketDto ticketDtoTemp = new TicketDto(userId, voyageId, foundVoyage.getCurrencyType(), foundVoyage.getAmount(), ticketDto.getPaymentType(), ticketDto.getName(), ticketDto.getSurname(), ticketDto.getEmail(), ticketDto.getPhoneNumber(), ticketDto.getGender(), ticketDto.getAge());
        TicketDto ticketPayment = paymentClient.createPayment(ticketDtoTemp);
        log.info(ticketDtoTemp.toString());
        return ticketPayment; // return ticketDtoTemp;
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
