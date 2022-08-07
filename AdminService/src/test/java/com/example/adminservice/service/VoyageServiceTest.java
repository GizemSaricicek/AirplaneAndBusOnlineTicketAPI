package com.example.adminservice.service;

import com.example.adminservice.dto.AdminDto;
import com.example.adminservice.dto.VoyageDto;
import com.example.adminservice.exception.OnlineTicketAppException;
import com.example.adminservice.model.Admin;
import com.example.adminservice.model.Voyage;
import com.example.adminservice.model.enums.VehicleType;
import com.example.adminservice.repository.AdminRepository;
import com.example.adminservice.repository.VoyageRepository;
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

    @Test
    @DisplayName("It should create voyage.")
    void it_should_create_voyage() {

        //when
        Voyage createVoyage = new Voyage(1, "testCountry", LocalDateTime.now(), VehicleType.AIRPLANE, 1000.0);
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
    @DisplayName("It should delete voyage.")
    void it_should_delete_voyage() {

        //when
        Voyage createVoyage = new Voyage(1, "testCountry", LocalDateTime.now(), VehicleType.AIRPLANE, 1000.0);

        AdminDto adminDto = new AdminDto(1, "testName", "testSurname", "testPW");
        Admin createAdmin = new Admin();
        createAdmin.setId(adminDto.getId());

        Mockito.when(adminRepository.findById(Mockito.any())).thenReturn(Optional.of(createAdmin));
        Mockito.when(voyageRepository.findById(Mockito.any())).thenReturn(Optional.of(createVoyage));


        //given
        Optional<Voyage> responseVoyage = voyageService.deleteVoyageById(1, 1);

        //then
        verify(voyageRepository, times(1)).save(Mockito.any());

        //gelen değer istenilen değer mi verify ediliyor.
        assertThat(responseVoyage).isEqualTo(Optional.of(createVoyage));
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
        AdminDto adminDto = new AdminDto(1, "testName", "testSurname", "testpw");
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
        Mockito.when(voyageRepository.findByStatus(Mockito.anyBoolean())).thenReturn(voyages);

        //then
        List<Voyage> responses = voyageService.getAllVoyages();
        verify(voyageRepository, times(1)).findByStatus(Mockito.anyBoolean());
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
        Mockito.when(voyageRepository.findByCountryAndStatus(Mockito.any(), Mockito.anyBoolean())).thenReturn(voyages);


        //given
        Voyage responseVoyage = new Voyage();
        responseVoyage.setCountry(country);
        List<Voyage> responseVoyages = voyageService.getVoyagesByCountry(country);

        //then
        verify(voyageRepository, times(1)).findByCountryAndStatus(country, true);
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
        Mockito.when(voyageRepository.findByTypeAndStatus(Mockito.any(),Mockito.anyBoolean())).thenReturn(voyages);

        //given
        Voyage responseVoyage = new Voyage();
        responseVoyage.setType(vehicleType);
        List<Voyage> responses = voyageService.getVoyagesByVehicleType(vehicleType);

        //then
        verify(voyageRepository, times(1)).findByTypeAndStatus(vehicleType, true);
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
        Mockito.when(voyageRepository.findByVoyageDateAndStatus(Mockito.any(), Mockito.anyBoolean())).thenReturn(voyages);

        //given
        Voyage responseVoyage = new Voyage();
        responseVoyage.setVoyageDate(localDateTime);
        List<Voyage> responses = voyageService.getVoyagesByDate(localDateTime);

        //then
        verify(voyageRepository, times(1)).findByVoyageDateAndStatus(localDateTime, true);
        assertThat(responses).isNotNull();
        assertThat(responses).isEqualTo(voyages);
    }

    @Test
    @DisplayName("It should return voyage by İD.")
    void it_should_return_voyage_by_id() {
        //when
        Integer voyageId = 1;
        Voyage voyage = new Voyage();
        voyage.setId(voyageId);
        Mockito.when(voyageRepository.findById(Mockito.any())).thenReturn(Optional.of(voyage));

        //given
        Voyage responseVoyage = new Voyage();
        responseVoyage.setId(voyageId);
        Optional<Voyage> response = voyageService.getVoyageById(voyageId);

        //then
        verify(voyageRepository, times(1)).findById(voyageId);
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(Optional.of(voyage));
    }

}
