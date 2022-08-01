package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.dto.AdminDto;
import com.example.airplaneandbusonlineticketapi.exception.OnlineTicketAppException;
import com.example.airplaneandbusonlineticketapi.model.Admin;
import com.example.airplaneandbusonlineticketapi.repository.AdminRepository;
import com.example.airplaneandbusonlineticketapi.security.Encryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private Encryptor encryptor;

    private AdminDto prepareAdmin() {
        AdminDto adminDto = new AdminDto(1, "testName","testSurname", "testpw");
        return adminDto;
    }

    @Test
    @DisplayName("It should create admin.")
    void it_should_create_admin() {

        //when
        Admin createAdmin = new Admin(1, "testName", "testSurname", "testpw");
        Mockito.when(adminRepository.save(Mockito.any())).thenReturn(createAdmin);

        //given

        AdminDto adminDto = prepareAdmin();
        Admin responseAdmin = adminService.createAdmin(adminDto);

        //then

        //userRepository herhangi bir obje ile çağrılabilir.
        //save metodu çağrılabilmiş mi verify ediliyor.
        verify(adminRepository, times(1)).save(Mockito.any());

        //gelen değer istenilen değer mi verify ediliyor.
        assertThat(responseAdmin.getEmail()).isEqualTo(adminDto.getEmail());
    }

    @Test
    @DisplayName("It should throw OnlineTicketAppException when admin already exists.")
    void it_should_throw_OnlineTicketAppException_when_admin_already_exist() {

        OnlineTicketAppException expectedException = new OnlineTicketAppException("user already exist.");

        Mockito.when(adminRepository.findByEmail(Mockito.anyString())).thenThrow(expectedException);

        Throwable exception = catchThrowable(() -> adminRepository.findByEmail(Mockito.anyString()));

        assertThat(exception).isInstanceOf(OnlineTicketAppException.class);
        assertThat(exception.getMessage()).isEqualTo(expectedException.getMessage());

    }

    @Test
    @DisplayName("It should return password.")
    void it_should_return_password() {

        //when
        String password = "testpw";

        //given
        Admin responseAdmin = new Admin();
        AdminDto adminDto = prepareAdmin();
        responseAdmin.setPassword(adminDto.getPassword());
        Mockito.when(encryptor.encryptGivenPassword(password)).thenReturn(String.valueOf(Optional.of(responseAdmin.getPassword())));

        //then
        boolean response = adminRepository.findByPassword(encryptor.encryptGivenPassword(password)).isPresent();

        if (response) {
            String responsePassword = String.valueOf(adminRepository.findByPassword(encryptor.encryptGivenPassword(password)).get());
            assertThat(responsePassword).isNotNull();
            assertThat(responsePassword).isEqualTo(responseAdmin.getPassword());
        }
        verify(adminRepository, times(1)).findByPassword(encryptor.encryptGivenPassword(password));
    }

    @Test
    @DisplayName("It should return admin by email.")
    void it_should_return_admin_by_email() {

        //when
        String email = "test@gmail.com";
        String password = "testPW";

        //given
        Admin responseAdmin = new Admin();
        AdminDto adminDto = prepareAdmin();
        responseAdmin.setEmail(adminDto.getEmail());
        responseAdmin.setPassword(adminDto.getPassword());
        Mockito.when(adminRepository.findByEmail(email)).thenReturn(Optional.of(responseAdmin));

        //then
        Optional<Admin> response = adminRepository.findByEmail(email);
        verify(adminRepository, times(1)).findByEmail(email);
        assertThat(response).isNotNull();
        assertThat(response.get().getEmail()).isEqualTo(responseAdmin.getEmail());
        assertThat(response.get().getPassword()).isEqualTo(responseAdmin.getPassword());

    }
}
