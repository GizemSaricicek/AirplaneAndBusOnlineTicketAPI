package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.client.AdminClient;
import com.example.airplaneandbusonlineticketapi.client.PaymentClient;
import com.example.airplaneandbusonlineticketapi.dto.ConfigurationDto;
import com.example.airplaneandbusonlineticketapi.dto.PassengerDto;
import com.example.airplaneandbusonlineticketapi.dto.TicketDto;
import com.example.airplaneandbusonlineticketapi.exception.OnlineTicketAppException;
import com.example.airplaneandbusonlineticketapi.model.Passenger;
import com.example.airplaneandbusonlineticketapi.model.User;
import com.example.airplaneandbusonlineticketapi.model.Voyage;
import com.example.airplaneandbusonlineticketapi.model.enums.CurrencyType;
import com.example.airplaneandbusonlineticketapi.model.enums.GenderType;
import com.example.airplaneandbusonlineticketapi.model.enums.UserType;
import com.example.airplaneandbusonlineticketapi.model.enums.VehicleType;
import com.example.airplaneandbusonlineticketapi.repository.UserRepository;
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
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;
    @Mock
    private PaymentClient paymentClient;
    @Mock
    private AdminClient adminClient;
    @Mock
    private PassengerService passengerService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RabbitMqService rabbitMqService;

    private Voyage prepareVoyage() {
        Voyage voyage = new Voyage(1, "testCountry", "testDeparture", LocalDateTime.now(), 100.00, true, CurrencyType.TL, VehicleType.AIRPLANE);
        return voyage;
    }

    private TicketDto prepareTicket() {
        TicketDto ticketDto = new TicketDto(1, 1, CurrencyType.TL, 100.00, VehicleType.AIRPLANE);
        return ticketDto;
    }

    @Test
    @DisplayName("It should return ticket by user id.")
    void it_should_return_ticket_by_user_id() {
        //when
        Integer userId = 1;
        TicketDto createTicketDto = prepareTicket();
        List<TicketDto> tickets = new ArrayList<>();
        tickets.add(createTicketDto);
        Mockito.when(paymentClient.getTicketsByUserId(createTicketDto.getUserId())).thenReturn(tickets);

        //given
        List<TicketDto> responses = paymentClient.getTicketsByUserId(userId);

        //then
        verify(paymentClient, times(1)).getTicketsByUserId(userId);
        assertThat(responses).isNotNull();
        assertThat(responses).isEqualTo(tickets);
    }

    @Test
    @DisplayName("It should return ticket by vehicle type.")
    void it_should_return_ticket_by_vehicle_type() {
        //when
        VehicleType vehicleType = VehicleType.AIRPLANE;
        Integer voyageId = 1;

        TicketDto createTicketDto = prepareTicket();
        List<TicketDto> tickets = new ArrayList<>();
        tickets.add(createTicketDto);
        Mockito.when(paymentClient.getTicketsByVoyageId(voyageId)).thenReturn(tickets);

        //given
        List<TicketDto> responses = paymentClient.getTicketsByVoyageId(voyageId);

        //then
        verify(paymentClient, times(1)).getTicketsByVoyageId(voyageId);
        assertThat(responses).isNotNull();
        assertThat(responses).isEqualTo(tickets);
    }

    @Test
    @DisplayName("It should return ticket by user id and voyage id.")
    void it_should_return_ticket_by_user_id_and_voyage_id() {
        //when
        Integer userId = 1;
        Integer voyageId = 1;
        TicketDto createTicketDto = prepareTicket();
        List<TicketDto> tickets = new ArrayList<>();
        tickets.add(createTicketDto);
        Mockito.when(paymentClient.getTicketsByUserIdAndVoyageId(createTicketDto.getUserId(), createTicketDto.getVoyageId())).thenReturn(tickets);

        //given
        List<TicketDto> responses = paymentClient.getTicketsByUserIdAndVoyageId(userId, voyageId);

        //then
        verify(paymentClient, times(1)).getTicketsByUserIdAndVoyageId(userId, voyageId);
        assertThat(responses).isNotNull();
        assertThat(responses).isEqualTo(tickets);
    }

    @Test
    @DisplayName("It should return voyage by id.")
    void it_should_return_voyage_by_id() {
        //when
        Integer voyageId = 1;
        Voyage createVoyage = prepareVoyage();

        Mockito.when(adminClient.getVoyageById(voyageId)).thenReturn(Optional.of(createVoyage));

        //given
        Optional<Voyage> response = adminClient.getVoyageById(voyageId);

        //then
        verify(adminClient, times(1)).getVoyageById(voyageId);
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(Optional.of(createVoyage));
    }

    @Test
    @DisplayName("It should return voyage by type.")
    void it_should_return_voyage_by_type() {
        //when
        VehicleType vehicleType = VehicleType.AIRPLANE;

        Voyage createVoyage = prepareVoyage();
        List<Voyage> voyages = new ArrayList<>();
        voyages.add(createVoyage);

        Mockito.when(adminClient.getVoyagesByVehicleType(vehicleType)).thenReturn(voyages);

        //given
        List<Voyage> response = adminClient.getVoyagesByVehicleType(vehicleType);

        //then
        verify(adminClient, times(1)).getVoyagesByVehicleType(vehicleType);
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(voyages);
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
        List<TicketDto> responses = ticketService.getAllTickets();

        //then
        verify(paymentClient, times(1)).getAllTickets();
        assertThat(responses).isNotNull();
        assertThat(responses).isEqualTo(tickets);
    }

    @Test
    @DisplayName("It should return user by id.")
    void it_should_return_user_by_id() {

        //when
        Integer id = 1;

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
    @DisplayName("It should return user by type.")
    void it_should_return_user_by_type() {

        //when
        UserType userType = UserType.INDIVIDUAL;
        Integer userId = 1;

        //given
        User responseUser = new User();
        responseUser.setUserType(UserType.INDIVIDUAL);
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(responseUser));

        //then
        Optional<User> response = userRepository.findById(userId);
        verify(userRepository, times(1)).findById(userId);
        assertThat(response).isNotNull();
        assertThat(response.get().getUserType()).isEqualTo(responseUser.getUserType());
    }

    @Test
    @DisplayName("It should create passenger.")
    void it_should_create_passenger() {

        //when
        Integer voyageId = 1;
        Passenger createPassenger = new Passenger(1, "testName", "testSurname", "test@gmail.com", "1111111111", 30, voyageId, GenderType.MALE, new User());

        Mockito.when(passengerService.createPassenger(Mockito.any(), Mockito.any())).thenReturn(createPassenger);

        //given
        User user = new User();
        PassengerDto passengerDto = new PassengerDto();
        passengerDto.setGender(GenderType.MALE);
        passengerDto.setEmail("test@gmail.com");
        passengerDto.setAge(30);
        passengerDto.setSurname("testSurname");
        passengerDto.setName("testName");
        passengerDto.setPhoneNumber("1111111111");
        passengerDto.setVoyageId(voyageId);
        passengerDto.setUser(user);

        Passenger responsePassenger = passengerService.createPassenger(user, passengerDto);

        //then
        verify(passengerService, times(1)).createPassenger(Mockito.any(), Mockito.any());
        //gelen değer istenilen değer mi verify ediliyor.
        assertThat(responsePassenger).isEqualTo(createPassenger);

    }

    @Test
    @DisplayName("It should throw OnlineTicketAppException when voyage not found.")
    void it_should_throw_OnlineTicketAppException_when_voyage_not_found() {

        OnlineTicketAppException expectedException = new OnlineTicketAppException("voyage not found.");

        Mockito.when(adminClient.getVoyageById(Mockito.anyInt())).thenThrow(expectedException);

        Throwable exception = catchThrowable(() -> adminClient.getVoyageById(Mockito.anyInt()));

        assertThat(exception).isInstanceOf(OnlineTicketAppException.class);
        assertThat(exception.getMessage()).isEqualTo(expectedException.getMessage());

    }

    @Test
    @DisplayName("It should throw OnlineTicketAppException when user not found.")
    void it_should_throw_OnlineTicketAppException_when_user_not_found() {

        OnlineTicketAppException expectedException = new OnlineTicketAppException("user not found.");

        Mockito.when(userRepository.findById(Mockito.anyInt())).thenThrow(expectedException);

        Throwable exception = catchThrowable(() -> userRepository.findById(Mockito.anyInt()));

        assertThat(exception).isInstanceOf(OnlineTicketAppException.class);
        assertThat(exception.getMessage()).isEqualTo(expectedException.getMessage());
    }

    @Test
    @DisplayName("It should throw OnlineTicketAppException when ticket not found.")
    void it_should_throw_OnlineTicketAppException_when_ticket_not_found() {

        OnlineTicketAppException expectedException = new OnlineTicketAppException("there is no ticket for this user.");

        Mockito.when(paymentClient.getTicketsByUserId(Mockito.anyInt())).thenThrow(expectedException);

        Throwable exception = catchThrowable(() -> paymentClient.getTicketsByUserId(Mockito.anyInt()));

        assertThat(exception).isInstanceOf(OnlineTicketAppException.class);
        assertThat(exception.getMessage()).isEqualTo(expectedException.getMessage());
    }

    @Test
    @DisplayName("It should create ticket.")
    void it_should_create_ticket() {

        //when
        TicketDto ticketDto = prepareTicket();
        Integer userId = 1;
        Integer voyageId = 1;

        List<TicketDto> ticketDtoList = new ArrayList<>();
        ticketDtoList.add(ticketDto);
        Mockito.when(paymentClient.createPayments(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(), Mockito.anyDouble(), Mockito.any(), Mockito.any())).thenReturn(ticketDtoList);

        User createUser = new User(1, "testName", "testpw", UserType.INDIVIDUAL, "test@gmail.com", "1111111111");
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(createUser));

        Voyage createVoyage = new Voyage("testCountry", LocalDateTime.now(), VehicleType.AIRPLANE, 1000.0);
        Mockito.when(adminClient.getVoyageById(Mockito.anyInt())).thenReturn(Optional.of(createVoyage));

        //given
        List<TicketDto> responseTicketDtoList = new ArrayList<>();
        TicketDto responseticketDto = new TicketDto(1, 1, CurrencyType.TL, 100.00, VehicleType.AIRPLANE);
        responseticketDto.setGender(GenderType.FEMALE);
        //responseticketDto.setGender(GenderType.MALE);
        responseTicketDtoList.add(responseticketDto);

        List<TicketDto> responseTickets = ticketService.createTickets(userId, voyageId,responseTicketDtoList);

        //then
        verify(paymentClient, times(1)).createPayments(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(), Mockito.anyDouble(), Mockito.any(), Mockito.any());
        verify(rabbitMqService, times(1)).sendConfiguration(Mockito.any(ConfigurationDto.class));

        //gelen değer istenilen değ er mi verify ediliyor.
        assertThat(responseTickets).isEqualTo(ticketDtoList);

    }
}
