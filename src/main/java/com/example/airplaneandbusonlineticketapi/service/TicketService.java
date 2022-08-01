package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.client.PaymentClient;
import com.example.airplaneandbusonlineticketapi.dto.ConfigurationDto;
import com.example.airplaneandbusonlineticketapi.dto.TicketDto;
import com.example.airplaneandbusonlineticketapi.exception.OnlineTicketAppException;
import com.example.airplaneandbusonlineticketapi.model.User;
import com.example.airplaneandbusonlineticketapi.model.Voyage;
import com.example.airplaneandbusonlineticketapi.model.enums.*;
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
    final Integer MAX_MALE_PASSENGER = 2;

    @Autowired
    private PaymentClient paymentClient;
    @Autowired
    private VoyageRepository voyageRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RabbitMqService rabbitMqService;

    public TicketDto createTicket(Integer userId, Integer voyageId, TicketDto ticketDto) {

        Voyage foundVoyage = voyageRepository.findById(voyageId).orElseThrow(() -> new OnlineTicketAppException("Voyage not found."));
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new OnlineTicketAppException("User not found."));

        List<TicketDto> passengerNumber = paymentClient.getTicketsByVehicleType(voyageId);
        log.info(passengerNumber.toString());

        if ((foundVoyage.getType().equals(VehicleType.AIRPLANE) && (passengerNumber.size() > (MAX_AIRPLANE_PASSENGER - 1)))
                || (foundVoyage.getType().equals(VehicleType.BUS) && (passengerNumber.size() > (MAX_BUS_PASSENGER - 1)))) {
            throw new OnlineTicketAppException("Cannot buy ticket. Vehicle capacity is full.");
        }

        List<TicketDto> ticketsNumber = paymentClient.getTicketsByUserIdAndVoyageId(userId, voyageId); // Integer olarak alabilirsin sonra sum() demek yerine.

        if ((foundUser.getUserType().equals(UserType.INDIVIDUAL) && ticketsNumber.size() > MAX_INDIVIDUAL_TICKET - 1) || (foundUser.getUserType().equals(UserType.CORPORATE) && ticketsNumber.size() > MAX_CORPORATE_TICKET - 1)) {
            throw new OnlineTicketAppException("Cannot buy ticket. Individual users can buy 5 ticket for a voyage and Corporate users can buy 20 tickets for a voyage ");
        }

        TicketDto ticketDtoTemp = new TicketDto(userId, voyageId, foundVoyage.getCurrencyType(), foundVoyage.getAmount(), ticketDto.getPaymentType(), ticketDto.getName(), ticketDto.getSurname(), ticketDto.getEmail(), ticketDto.getPhoneNumber(), ticketDto.getGender(), ticketDto.getAge());
        TicketDto ticketPayment = paymentClient.createPayment(ticketDtoTemp);
        log.info(ticketDtoTemp.toString());

        ConfigurationDto configurationDto = new ConfigurationDto();
        configurationDto.setTicketDto(ticketPayment);
        configurationDto.setConfigurationType(ConfigurationType.MESSAGE);

        rabbitMqService.sendEmail(configurationDto);
        return ticketPayment; // return ticketDtoTemp;
    }

    public List<TicketDto> getTicketsByUserId(Integer userId) {

        User foundUser = userRepository.findById(userId).orElseThrow(() -> new OnlineTicketAppException("User not found."));

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

    public List<TicketDto> createTickets(Integer userId, Integer voyageId, List<TicketDto> ticketDtos) {

        User foundUser = userRepository.findById(userId).orElseThrow(() -> new OnlineTicketAppException("User not found."));
        Voyage foundVoyage = voyageRepository.findById(voyageId).orElseThrow(() -> new OnlineTicketAppException("Voyage not found."));

        Integer malePassenger = Math.toIntExact(ticketDtos.stream().filter(ticketDto -> ticketDto.getGender().equals(GenderType.MALE)).count());
        if ((foundUser.getUserType().equals(UserType.INDIVIDUAL)) && malePassenger > MAX_MALE_PASSENGER) {
            throw new OnlineTicketAppException("Cannot buy s ticket. Individual users can buy max 2 tickets for man at a time.");
        }
//        TicketDto ticketDtoTemp = new TicketDto(userId, voyageId, foundVoyage.getCurrencyType(), foundVoyage.getAmount(), ticketDtos.get(i).getPaymentType(), ticketDtos.get(i).getName(), ticketDtos.get(i).getSurname(), ticketDtos.get(i).getEmail(), ticketDtos.get(i).getPhoneNumber(), ticketDtos.get(i).getGender(), ticketDtos.get(i).getAge());
        List<TicketDto> ticketPayments = paymentClient.createPayments(userId, voyageId, foundVoyage.getAmount(), ticketDtos);

        return ticketPayments;
    }
}
