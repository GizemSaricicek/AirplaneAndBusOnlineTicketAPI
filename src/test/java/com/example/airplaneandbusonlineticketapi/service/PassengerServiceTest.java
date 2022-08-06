package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.dto.PassengerDto;
import com.example.airplaneandbusonlineticketapi.model.Passenger;
import com.example.airplaneandbusonlineticketapi.model.User;
import com.example.airplaneandbusonlineticketapi.model.enums.GenderType;
import com.example.airplaneandbusonlineticketapi.model.enums.UserType;
import com.example.airplaneandbusonlineticketapi.repository.PassengerRepository;
import com.example.airplaneandbusonlineticketapi.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class PassengerServiceTest {

    @InjectMocks
    private PassengerService passengerService;

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("It should create passenger.")
    void it_should_create_passenger() {

        //when
        Passenger createPassenger = new Passenger("testName", "testSurname", "test@gmail.com", "1111111111", GenderType.MALE, 30, 1);
        Mockito.when(passengerRepository.save(Mockito.any())).thenReturn(createPassenger);

        User createUser = new User(1, "testName", "testpw", UserType.INDIVIDUAL, "test@gmail.com", "1111111111");
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(createUser);

        //given
        PassengerDto passengerDto = new PassengerDto();
        passengerDto.setGender(GenderType.MALE);
        passengerDto.setEmail("test@gmail.com");
        passengerDto.setAge(30);
        passengerDto.setSurname("testSurname");
        passengerDto.setName("testName");
        passengerDto.setPhoneNumber("1111111111");
        passengerDto.setVoyageId(1);
        Passenger responsePassenger = passengerService.createPassenger(createUser, passengerDto);

        //then
        verify(passengerRepository, times(1)).save(Mockito.any());

        //gelen değer istenilen değer mi verify ediliyor.
        assertThat(responsePassenger).isEqualTo(createPassenger);

    }
}
