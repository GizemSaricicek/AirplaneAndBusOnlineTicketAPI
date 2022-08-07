package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.client.AdminClient;
import com.example.airplaneandbusonlineticketapi.dto.VoyageDto;
import com.example.airplaneandbusonlineticketapi.exception.OnlineTicketAppException;
import com.example.airplaneandbusonlineticketapi.model.Voyage;
import com.example.airplaneandbusonlineticketapi.model.enums.VehicleType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class VoyageServiceTest {

    @InjectMocks
    private VoyageService voyageService;

    @Mock
    private AdminClient adminClient;

    @Test
    @DisplayName("It should return all voyages.")
    void it_should_return_all_voyages() {

        //given
        List<Voyage> voyages = new ArrayList<>();
        Mockito.when(adminClient.getAllVoyages()).thenReturn(voyages);

        //then
        List<Voyage> responses = voyageService.getCurrentVoyages();
        verify(adminClient, times(1)).getAllVoyages();
        assertThat(responses).isNotNull();
        assertThat(responses).isEqualTo(voyages);

    }

    @Test
    @DisplayName("It should return voyage by country.")
    void it_should_return_voyage_by_country() {

        //when
        String country = "testCountry";
        List<Voyage> voyages = new ArrayList<>();
        Voyage voyage = new Voyage();
        voyage.setCountry(country);
        voyages.add(voyage);
        Mockito.when(adminClient.getVoyagesByCountry(Mockito.any())).thenReturn(voyages);


        //given
        Voyage responseVoyage = new Voyage();
        responseVoyage.setCountry(country);
        List<Voyage> responseVoyages = voyageService.getVoyagesByCountry(country);

        //then
        verify(adminClient, times(1)).getVoyagesByCountry(country);
        assertThat(responseVoyages).isNotNull();
        assertThat(responseVoyages).isEqualTo(voyages);
    }

    @Test
    @DisplayName("It should return voyage by vehicle type.")
    void it_should_return_voyage_by_vehicleType() {
        //when
        VehicleType vehicleType = VehicleType.AIRPLANE;
        List<Voyage> voyages = new ArrayList<>();
        Voyage voyage = new Voyage();
        voyage.setType(vehicleType);
        voyages.add(voyage);
        Mockito.when(adminClient.getVoyagesByVehicleType(Mockito.any())).thenReturn(voyages);

        //given
        Voyage responseVoyage = new Voyage();
        responseVoyage.setType(vehicleType);
        List<Voyage> responses = voyageService.getVoyagesByVehicleType(vehicleType);

        //then
        verify(adminClient, times(1)).getVoyagesByVehicleType(vehicleType);
        assertThat(responses).isNotNull();
        assertThat(responses).isEqualTo(voyages);
    }

    @Test
    @DisplayName("It should return voyage by date.")
    void it_should_return_voyage_by_date() {
        //when
        LocalDateTime localDateTime = LocalDateTime.now();
        List<Voyage> voyages = new ArrayList<>();
        Voyage voyage = new Voyage();
        voyage.setVoyageDate(localDateTime);
        voyages.add(voyage);
        Mockito.when(adminClient.getVoyagesByDate(Mockito.any())).thenReturn(voyages);

        //given
        Voyage responseVoyage = new Voyage();
        responseVoyage.setVoyageDate(localDateTime);
        List<Voyage> responses = voyageService.getVoyagesByDate(localDateTime);

        //then
        verify(adminClient, times(1)).getVoyagesByDate(localDateTime);
        assertThat(responses).isNotNull();
        assertThat(responses).isEqualTo(voyages);
    }

    @Test
    @DisplayName("It should throw OnlineTicketAppException when there is no voyage.")
    void it_should_throw_OnlineTicketAppException_when_there_is_no_voyage() {

        OnlineTicketAppException expectedException = new OnlineTicketAppException("there is no voyage in the system.");

        Mockito.when(adminClient.getAllVoyages()).thenThrow(expectedException);

        Throwable exception = catchThrowable(() -> adminClient.getAllVoyages());

        assertThat(exception).isInstanceOf(OnlineTicketAppException.class);
        assertThat(exception.getMessage()).isEqualTo(expectedException.getMessage());

    }

    @Test
    @DisplayName("It should throw OnlineTicketAppException when there is no voyage for this country.")
    void it_should_throw_OnlineTicketAppException_when_there_is_no_voyage_for_this_country() {

        OnlineTicketAppException expectedException = new OnlineTicketAppException("there is no voyage for this country.");

        Mockito.when(adminClient.getVoyagesByCountry(Mockito.anyString())).thenThrow(expectedException);

        Throwable exception = catchThrowable(() -> adminClient.getVoyagesByCountry(Mockito.anyString()));

        assertThat(exception).isInstanceOf(OnlineTicketAppException.class);
        assertThat(exception.getMessage()).isEqualTo(expectedException.getMessage());

    }

    @Test
    @DisplayName("It should throw OnlineTicketAppException when there is no voyage for this vehicle type.")
    void it_should_throw_OnlineTicketAppException_when_there_is_no_voyage_for_this_vehicle_type() {

        OnlineTicketAppException expectedException = new OnlineTicketAppException("there is no voyage for this vehicle type.");

        Mockito.when(adminClient.getVoyagesByVehicleType(Mockito.any())).thenThrow(expectedException);

        Throwable exception = catchThrowable(() -> adminClient.getVoyagesByVehicleType(Mockito.any()));

        assertThat(exception).isInstanceOf(OnlineTicketAppException.class);
        assertThat(exception.getMessage()).isEqualTo(expectedException.getMessage());

    }

    @Test
    @DisplayName("It should throw OnlineTicketAppException when there is no voyage for this date.")
    void it_should_throw_OnlineTicketAppException_when_there_is_no_voyage_for_this_date() {

        OnlineTicketAppException expectedException = new OnlineTicketAppException("there is no voyage for this date.");

        Mockito.when(adminClient.getVoyagesByDate(Mockito.any())).thenThrow(expectedException);

        Throwable exception = catchThrowable(() -> adminClient.getVoyagesByDate(Mockito.any()));

        assertThat(exception).isInstanceOf(OnlineTicketAppException.class);
        assertThat(exception.getMessage()).isEqualTo(expectedException.getMessage());

    }
}
