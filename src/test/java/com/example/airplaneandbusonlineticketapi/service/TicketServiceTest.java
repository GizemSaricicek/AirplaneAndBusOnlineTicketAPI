package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.client.PaymentClient;
import com.example.airplaneandbusonlineticketapi.dto.AdminDto;
import com.example.airplaneandbusonlineticketapi.dto.TicketDto;
import com.example.airplaneandbusonlineticketapi.dto.UserDto;
import com.example.airplaneandbusonlineticketapi.dto.VoyageDto;
import com.example.airplaneandbusonlineticketapi.model.User;
import com.example.airplaneandbusonlineticketapi.model.Voyage;
import com.example.airplaneandbusonlineticketapi.model.enums.CurrencyType;
import com.example.airplaneandbusonlineticketapi.model.enums.VehicleType;
import com.example.airplaneandbusonlineticketapi.repository.UserRepository;
import com.example.airplaneandbusonlineticketapi.repository.VoyageRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;
    @Mock
    private PaymentClient paymentClient;
    @Mock
    private VoyageRepository voyageRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RabbitMqService rabbitMqService;

    private VoyageDto prepareVoyage() {
        VoyageDto voyageDto = new VoyageDto("testCountry", LocalDateTime.now(), VehicleType.AIRPLANE, 1000.0);
        return voyageDto;
    }

    private AdminDto prepareAdmin() {
        AdminDto adminDto = new AdminDto(1, "testName", "testSurname", "testpw");
        return adminDto;
    }

    private TicketDto prepareTicket() {
        TicketDto ticketDto = new TicketDto(1, 1, CurrencyType.TL, 100.00);
        return ticketDto;
    }

    private UserDto prepareUser() {
        UserDto userDto = new UserDto("testpw", "test@gmail.com");
        return userDto;
    }

    @Test
    @DisplayName("It should return ticket by user id.")
    void it_should_return_ticket_by_user_id() {
        //when
        Integer userId = 1;
        TicketDto createticketDto = prepareTicket();
        List<TicketDto> tickets = new ArrayList<>();
        tickets.add(createticketDto);
        Mockito.when(paymentClient.getTicketsByUserId(createticketDto.getUserId())).thenReturn(tickets);

        //given
        List<TicketDto> responses =paymentClient.getTicketsByUserId(userId);

        //then
        verify(paymentClient, times(1)).getTicketsByUserId(userId);
        assertThat(responses).isNotNull();
        assertThat(responses).isEqualTo(tickets);
    }

    @Test
    @DisplayName("It should return all tickets.")
    void it_should_return_all_tickets() {
        //when
        TicketDto createTicketDto = prepareTicket();
        List<TicketDto> tickets = new ArrayList<>();
        tickets.add(createTicketDto);
        Mockito.when(paymentClient.getAllTickets()).thenReturn(tickets);

        //given
        List<TicketDto> responses =paymentClient.getAllTickets();

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
    @DisplayName("It should return user by id.")
    void it_should_return_user_by_id() {

        //when
        Integer id= 1;

        //given
        User responseUser = new User();
        responseUser.setId(1);
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(responseUser));

        //then
        Optional<User> response = userRepository.findById(id);
        verify(userRepository, times(1)).findById(id);
        assertThat(response).isNotNull();
        assertThat(response.get().getId()).isEqualTo(responseUser.getId());
    }

    @Test
    @DisplayName("It should return voyage by id.")
    void it_should_return_voyage_by_id() {

        //when
        Integer id= 1;

        //given
        Voyage responseVoyage = new Voyage();
        responseVoyage.setId(1);
        Mockito.when(voyageRepository.findById(id)).thenReturn(Optional.of(responseVoyage));

        //then
        Optional<Voyage> response = voyageRepository.findById(id);
        verify(voyageRepository, times(1)).findById(id);
        assertThat(response).isNotNull();
        assertThat(response.get().getId()).isEqualTo(responseVoyage.getId());
    }
}
