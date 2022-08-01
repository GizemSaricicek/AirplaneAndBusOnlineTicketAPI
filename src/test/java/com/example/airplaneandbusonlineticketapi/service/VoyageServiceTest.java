package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.dto.AdminDto;
import com.example.airplaneandbusonlineticketapi.dto.VoyageDto;
import com.example.airplaneandbusonlineticketapi.exception.OnlineTicketAppException;
import com.example.airplaneandbusonlineticketapi.model.Admin;
import com.example.airplaneandbusonlineticketapi.model.Voyage;
import com.example.airplaneandbusonlineticketapi.model.enums.VehicleType;
import com.example.airplaneandbusonlineticketapi.repository.AdminRepository;
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
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class VoyageServiceTest {

    @InjectMocks
    private VoyageService voyageService;

    @Mock
    VoyageRepository voyageRepository;

    @Mock
    AdminRepository adminRepository;

    private VoyageDto prepareVoyage() {
        VoyageDto voyageDto = new VoyageDto("testCountry", LocalDateTime.now(), VehicleType.AIRPLANE, 1000.0);
        return voyageDto;
    }

    private AdminDto prepareAdmin() {
        AdminDto adminDto = new AdminDto(1, "testName", "testSurname", "testpw");
        return adminDto;
    }

    @Test
    @DisplayName("It should create voyage.")
    void it_should_create_voyage() {

        //when
        Voyage createVoyage = new Voyage("testCountry", LocalDateTime.now(), VehicleType.AIRPLANE, 1000.0);
        AdminDto adminDto = new AdminDto(1, "testName", "testSurname", "testPW");
        Admin createAdmin = new Admin();
        createAdmin.setId(adminDto.getId());
        Mockito.when(voyageRepository.save(Mockito.any())).thenReturn(createVoyage);
        Mockito.when(adminRepository.findById(createAdmin.getId())).thenReturn(Optional.of(createAdmin));

        //given
        VoyageDto voyageDto = prepareVoyage();
        Voyage responseVoyage = voyageService.createVoyage(1, voyageDto);

        //then
        verify(voyageRepository, times(1)).save(Mockito.any());

        //gelen değer istenilen değer mi verify ediliyor.
        assertThat(responseVoyage.getCountry()).isEqualTo(voyageDto.getCountry());
    }

    @Test
    @DisplayName("It should throw OnlineTicketAppException when admin not found.")
    void it_should_throw_OnlineTicketAppException_when_admin_not_found() {

        OnlineTicketAppException expectedException = new OnlineTicketAppException("admin not found.");

        Mockito.when(adminRepository.findById(Mockito.anyInt())).thenThrow(expectedException);

        Throwable exception = catchThrowable(() -> adminRepository.findById(Mockito.anyInt()));

        assertThat(exception).isInstanceOf(OnlineTicketAppException.class);
        assertThat(exception.getMessage()).isEqualTo(expectedException.getMessage());

    }

    @Test
    @DisplayName("It should return admin by id.")
    void it_should_return_admin_by_id() {
        //when
        Integer id = 1;

        //given
        Admin responseAdmin = new Admin();
        AdminDto adminDto = prepareAdmin();
        responseAdmin.setId(adminDto.getId());
        Mockito.when(adminRepository.findById(id)).thenReturn(Optional.of(responseAdmin));

        //then
        Optional<Admin> response = adminRepository.findById(id);
        verify(adminRepository, times(1)).findById(id);
        assertThat(response).isNotNull();
        assertThat(response.get().getId()).isEqualTo(responseAdmin.getId());
    }

    @Test
    @DisplayName("It should return all voyages.")
    void it_should_return_all_voyages() {

        //given
        List<Voyage> voyages = new ArrayList<>();
        Mockito.when(voyageRepository.findAll()).thenReturn(voyages);

        //then
        List<Voyage> responses = voyageRepository.findAll();
        verify(voyageRepository, times(1)).findAll();
        assertThat(responses).isNotNull();
    }

    @Test
    @DisplayName("It should return voyage by country.")
    void it_should_return_voyage_by_country() {
        //when
        String country = "testCountry";

        //given
        Voyage responseVoyage = new Voyage();
        VoyageDto voyageDto = prepareVoyage();
        responseVoyage.setCountry(voyageDto.getCountry());
        List<Voyage> voyages = new ArrayList<>();
        voyages.add(responseVoyage);
        Mockito.when(voyageRepository.findByCountry(country)).thenReturn(voyages);

        //then
        List<Voyage> responses =voyageRepository.findByCountry(country);
        verify(voyageRepository, times(1)).findByCountry(country);
        assertThat(responses).isNotNull();
        assertThat(responses).isEqualTo(voyages);
    }

    @Test
    @DisplayName("It should return voyage by vehicle type.")
    void it_should_return_voyage_by_vehicleType() {
        //when
        VehicleType  vehicleType= VehicleType.AIRPLANE;

        //given
        Voyage responseVoyage = new Voyage();
        VoyageDto voyageDto = prepareVoyage();
        responseVoyage.setType(voyageDto.getType());
        List<Voyage> voyages = new ArrayList<>();
        voyages.add(responseVoyage);
        Mockito.when(voyageRepository.findByType(vehicleType)).thenReturn(voyages);

        //then
        List<Voyage> responses =voyageRepository.findByType(vehicleType);
        verify(voyageRepository, times(1)).findByType(vehicleType);
        assertThat(responses).isNotNull();
        assertThat(responses).isEqualTo(voyages);
    }

    @Test
    @DisplayName("It should return voyage by date.")
    void it_should_return_voyage_by_date() {
        //when
        LocalDateTime localDateTime = LocalDateTime.now();

        //given
        Voyage responseVoyage = new Voyage();
        VoyageDto voyageDto = prepareVoyage();
        responseVoyage.setVoyageDate(voyageDto.getVoyageDate());
        List<Voyage> voyages = new ArrayList<>();
        voyages.add(responseVoyage);
        Mockito.when(voyageRepository.findByVoyageDate(localDateTime)).thenReturn(voyages);

        //then
        List<Voyage> responses =voyageRepository.findByVoyageDate(localDateTime);
        verify(voyageRepository, times(1)).findByVoyageDate(localDateTime);
        assertThat(responses).isNotNull();
        assertThat(responses).isEqualTo(voyages);
    }
}
