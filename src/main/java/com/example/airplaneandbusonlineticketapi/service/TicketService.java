package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.client.AdminClient;
import com.example.airplaneandbusonlineticketapi.client.PaymentClient;
import com.example.airplaneandbusonlineticketapi.dto.ConfigurationDto;
import com.example.airplaneandbusonlineticketapi.dto.PassengerDto;
import com.example.airplaneandbusonlineticketapi.dto.TicketDto;
import com.example.airplaneandbusonlineticketapi.exception.OnlineTicketAppException;
import com.example.airplaneandbusonlineticketapi.model.User;
import com.example.airplaneandbusonlineticketapi.model.Voyage;
import com.example.airplaneandbusonlineticketapi.model.enums.ConfigurationType;
import com.example.airplaneandbusonlineticketapi.model.enums.GenderType;
import com.example.airplaneandbusonlineticketapi.model.enums.UserType;
import com.example.airplaneandbusonlineticketapi.model.enums.VehicleType;
import com.example.airplaneandbusonlineticketapi.repository.UserRepository;
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
    private AdminClient adminClient;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RabbitMqService rabbitMqService;

    @Autowired
    private PassengerService passengerService;

    public List<TicketDto> getTicketsByUserId(Integer userId) {

        User foundUser = userRepository.findById(userId).orElseThrow(() -> new OnlineTicketAppException("User not found."));

        return paymentClient.getTicketsByUserId(userId);


    }

    public List<TicketDto> getAllTickets() {
        List<TicketDto> tickets = paymentClient.getAllTickets();

        if(tickets.isEmpty()){
            throw new OnlineTicketAppException("There is no ticket on the system.");
        }
        return tickets;
    }

    public List<TicketDto> createTickets(Integer userId, Integer voyageId, List<TicketDto> ticketDtos) {

        // user ve voyage kontrolü
        Voyage foundVoyage = adminClient.getVoyageById(voyageId).orElseThrow(() -> new OnlineTicketAppException("Voyage not found."));
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new OnlineTicketAppException("User not found."));

        // Maksimum 2 erkek yolcu kontrolü

        Integer malePassenger = Math.toIntExact(ticketDtos.stream().filter(ticketDto -> ticketDto.getGender().equals(GenderType.MALE)).count());
        if ((foundUser.getUserType().equals(UserType.INDIVIDUAL)) && malePassenger > MAX_MALE_PASSENGER) {
            throw new OnlineTicketAppException("Cannot buy s ticket. Individual users can buy max 2 tickets for man at a time.");
        }

        //Kapasite kontrolü

        // girilen seyehat'in biletlerinin sayısı
        Integer passengerNumber = paymentClient.getTicketsByVoyageId(voyageId).size();
        log.info(passengerNumber.toString());

        // girilen seyehatin araç türü
        VehicleType voyageVehicle = foundVoyage.getType();

        if ((voyageVehicle.equals(VehicleType.AIRPLANE) && (passengerNumber > (MAX_AIRPLANE_PASSENGER - 1)))
                || (voyageVehicle.equals(VehicleType.BUS) && (passengerNumber > (MAX_BUS_PASSENGER - 1)))) {
            throw new OnlineTicketAppException("Cannot buy ticket. Vehicle capacity is full.");
        }

        // Kullanıcıların max bilet alma kontrolü

        //girilen user ve voyage bilgilerine sahip bilet sayısı
        Integer ticketsNumber = paymentClient.getTicketsByUserIdAndVoyageId(foundUser.getId(), voyageId).size(); // Integer olarak alabilirsin sonra sum() demek yerine.

        // girilen kullanıcının türü
        UserType userType = foundUser.getUserType();

        if ((userType.equals(UserType.INDIVIDUAL) && ticketsNumber > MAX_INDIVIDUAL_TICKET - 1) || (userType.equals(UserType.CORPORATE) && ticketsNumber > MAX_CORPORATE_TICKET - 1)) {
            throw new OnlineTicketAppException("Cannot buy ticket. Individual users can buy 5 ticket for a voyage and Corporate users can buy 20 tickets for a voyage ");
        }

        // Ödeme alınması
        List<TicketDto> ticketPayments = paymentClient.createPayments(userId, voyageId, foundVoyage.getCurrencyType(), foundVoyage.getAmount(), foundVoyage.getType(), ticketDtos);

        // Passenger oluşumu
        PassengerDto passengerDto = new PassengerDto();

        for (int k = 0; k < ticketPayments.size(); k++) {
            passengerDto.setName(ticketPayments.get(k).getName());
            passengerDto.setSurname(ticketPayments.get(k).getSurname());
            passengerDto.setEmail(ticketPayments.get(k).getEmail());
            passengerDto.setPhoneNumber(ticketPayments.get(k).getPhoneNumber());
            passengerDto.setAge(ticketPayments.get(k).getAge());
            passengerDto.setVoyageId(foundVoyage.getId());
            passengerDto.setGender(ticketPayments.get(k).getGender());
            passengerService.createPassenger(foundUser, passengerDto);
        }

        for (int i = 0; i < ticketPayments.size(); i++) {
            // Mesaj iletimi ve mesaj iletimi için configurationDto oluşturulması
            ConfigurationDto configurationDto = new ConfigurationDto();
            ticketPayments.get(i).setCurrencyType(foundVoyage.getCurrencyType());
//            ticketPayments.get(i).setCountry(foundVoyage.getCountry());
//            ticketPayments.get(i).setDeparture(foundVoyage.getDeparture());
            configurationDto.setTicketDto(ticketPayments.get(i));
            configurationDto.setConfigurationType(ConfigurationType.MESSAGE);
            rabbitMqService.sendConfiguration(configurationDto);
//            log.info(ticketPayments.get(i).getDeparture());
        }

        return ticketPayments;
    }
}
