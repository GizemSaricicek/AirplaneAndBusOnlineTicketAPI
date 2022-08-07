package com.example.ticketpaymentservice.service;

import com.example.ticketpaymentservice.dto.TicketDto;
import com.example.ticketpaymentservice.model.Ticket;
import com.example.ticketpaymentservice.model.enums.CurrencyType;
import com.example.ticketpaymentservice.model.enums.VehicleType;
import com.example.ticketpaymentservice.repository.TicketRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;
    @Mock
    private TicketRepository ticketRepository;

    private TicketDto prepareTicket() {
        TicketDto ticketDto = new TicketDto(1, 1, CurrencyType.TL, 100.00, VehicleType.AIRPLANE);
        return ticketDto;
    }

    @Test
    @DisplayName("It should create payments.")
    void it_should_create_payment() {
        //when
        Ticket createTicket = new Ticket();
        List<Ticket> createTicketList = new ArrayList<>();
        Integer userId = 1;
        Integer voyageId = 1;
        VehicleType vehicleType = VehicleType.AIRPLANE;
        createTicket.setUserId(1);
        createTicket.setVoyageId(1);
        createTicket.setAmount(100.00);
        createTicket.setCurrencyType(CurrencyType.TL);

        createTicketList.add(createTicket);
        Mockito.when(ticketRepository.save(Mockito.any())).thenReturn(createTicket);

        //given
        List<TicketDto> ticketDtoList = new ArrayList<>();
        TicketDto ticketDto = new TicketDto();
        ticketDto.setCurrencyType(CurrencyType.TL);
        ticketDtoList.add(ticketDto);
        List<Ticket> responseTickets = paymentService.createPayments(userId, voyageId, CurrencyType.TL, 100.00, vehicleType, ticketDtoList);

        //then
        verify(ticketRepository, times(1)).save(Mockito.any());
        assertThat(responseTickets).isNotNull();

    }

    @Test
    @DisplayName("It should get ticket by user id.")
    void it_should_get_ticket_by_user_id() {
        Integer userId = 1;
        Ticket createTicket = new Ticket();
        createTicket.setUserId(userId);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(createTicket);
        Mockito.when(ticketRepository.findByUserId(userId)).thenReturn(tickets);

        //given
        List<Ticket> responses = paymentService.getTicketByUserId(userId);

        //then
        verify(ticketRepository, times(1)).findByUserId(userId);
        assertThat(responses).isNotNull();
        assertThat(responses).isEqualTo(tickets);
    }

    @Test
    @DisplayName("It should get all tickets.")
    void it_should_get_all_tickets() {
        Ticket createTicket = new Ticket();
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(createTicket);
        Mockito.when(ticketRepository.findAll()).thenReturn(tickets);

        //given
        List<Ticket> responses = paymentService.getAllTickets();

        //then
        verify(ticketRepository, times(1)).findAll();
        assertThat(responses).isNotNull();
        assertThat(responses).isEqualTo(tickets);
    }

    @Test
    @DisplayName("It should get ticket by currency type.")
    void it_should_get_tickets_by_currency_type() {
        //when
        CurrencyType currencyType = CurrencyType.TL;
        Ticket createTicket = new Ticket();
        createTicket.setCurrencyType(currencyType);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(createTicket);
        Mockito.when(ticketRepository.findByCurrencyType(currencyType)).thenReturn(tickets);

        //given
        List<Ticket> responses = paymentService.getTicketByCurrencyType(currencyType);

        //then
        verify(ticketRepository, times(1)).findByCurrencyType(currencyType);
        assertThat(responses).isNotNull();
        assertThat(responses).isEqualTo(tickets);
    }


    @Test
    @DisplayName("It should get tickets by user id and voyage id")
    void it_should_get_tickets_by_user_id_and_voyage_id() {
        //when
        Integer userId = 1;
        Integer voyageId = 1;
        Ticket createTicket = new Ticket();
        createTicket.setVoyageId(voyageId);
        createTicket.setUserId(userId);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(createTicket);
        Mockito.when(ticketRepository.findByUserIdAndVoyageId(createTicket.getUserId(), createTicket.getVoyageId())).thenReturn(tickets);

        //given
        List<Ticket> responses = paymentService.getTicketsByUserIdAndVoyageId(userId, voyageId);

        //then
        verify(ticketRepository, times(1)).findByUserIdAndVoyageId(userId, voyageId);
        assertThat(responses).isNotNull();
        assertThat(responses).isEqualTo(tickets);
    }

    @Test
    @DisplayName("It should get tickets by vehicle type")
    void it_should_get_tickets_by_vehicle_type() {
        VehicleType vehicleType = VehicleType.BUS;
        Ticket createTicket = new Ticket();
        createTicket.setVehicleType(vehicleType);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(createTicket);
        Mockito.when(ticketRepository.findByVehicleType(vehicleType)).thenReturn(tickets);

        //given
        List<Ticket> responses = paymentService.getTicketsByVehicleType(vehicleType);

        //then
        verify(ticketRepository, times(1)).findByVehicleType(vehicleType);
        assertThat(responses).isNotNull();
        assertThat(responses).isEqualTo(tickets);
    }

}
