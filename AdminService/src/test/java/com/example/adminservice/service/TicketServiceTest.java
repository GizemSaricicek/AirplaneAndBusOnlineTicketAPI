package com.example.adminservice.service;

import com.example.adminservice.client.PaymentClient;
import com.example.adminservice.dto.TicketDto;

import com.example.adminservice.exception.OnlineTicketAppException;
import com.example.adminservice.model.enums.CurrencyType;
import com.example.adminservice.model.enums.VehicleType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;
    @Mock
    private PaymentClient paymentClient;

    private TicketDto prepareTicket() {
        TicketDto ticketDto = new TicketDto(1, 1, CurrencyType.TL, 100.00, VehicleType.AIRPLANE);
        return ticketDto;
    }

    @Test
    @DisplayName("It should return all tickets.")
    void it_should_return_all_tickets() {
        TicketDto createTicketDto = prepareTicket();
        List<TicketDto> tickets = new ArrayList<>();
        tickets.add(createTicketDto);
        Mockito.when(paymentClient.getAllTickets()).thenReturn(tickets);

        //given
        List<TicketDto> responses = ticketService.getAllTickets();

        //then
        verify(paymentClient, times(1)).getAllTickets();
        assertThat(responses).isNotNull();
        assertThat(responses).isEqualTo(tickets);
    }

    @Test
    @DisplayName("It should return tickets by currency type.")
    void it_should_return_tickets_by_currency_type() {
        //when
        CurrencyType currencyType = CurrencyType.TL;
        TicketDto createTicketDto = prepareTicket();
        List<TicketDto> tickets = new ArrayList<>();
        tickets.add(createTicketDto);
        Mockito.when(paymentClient.getTicketsByCurrencyType(currencyType)).thenReturn(tickets);

        //given
        List<TicketDto> responses =paymentClient.getTicketsByCurrencyType(currencyType);

        //then
        verify(paymentClient, times(1)).getTicketsByCurrencyType(currencyType);
        assertThat(responses).isNotNull();
        assertThat(responses).isEqualTo(tickets);
    }

    @Test
    @DisplayName("It should throw OnlineTicketAppException when tickets not found in the system.")
    void it_should_throw_OnlineTicketAppException_when_ticket_not_found() {

        OnlineTicketAppException expectedException = new OnlineTicketAppException("there is no ticket in the system.");

        Mockito.when(paymentClient.getAllTickets()).thenThrow(expectedException);

        Throwable exception = catchThrowable(() -> paymentClient.getAllTickets());

        assertThat(exception).isInstanceOf(OnlineTicketAppException.class);
        assertThat(exception.getMessage()).isEqualTo(expectedException.getMessage());
    }

    @Test
    @DisplayName("It should throw OnlineTicketAppException when tickets not found for this currency type.")
    void it_should_throw_OnlineTicketAppException_when_ticket_not_found_for_this_currency_type() {

        OnlineTicketAppException expectedException = new OnlineTicketAppException("there is no ticket in the system.");

        Mockito.when(paymentClient.getTicketsByCurrencyType(Mockito.any())).thenThrow(expectedException);

        Throwable exception = catchThrowable(() -> paymentClient.getTicketsByCurrencyType(Mockito.any()));

        assertThat(exception).isInstanceOf(OnlineTicketAppException.class);
        assertThat(exception.getMessage()).isEqualTo(expectedException.getMessage());
    }

}


