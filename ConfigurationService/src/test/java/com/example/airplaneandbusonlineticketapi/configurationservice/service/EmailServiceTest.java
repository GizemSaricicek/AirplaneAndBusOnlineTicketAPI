package com.example.airplaneandbusonlineticketapi.configurationservice.service;

import com.example.airplaneandbusonlineticketapi.configurationservice.dto.EmailDto;
import com.example.airplaneandbusonlineticketapi.configurationservice.model.Email;
import com.example.airplaneandbusonlineticketapi.configurationservice.repository.EmailRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class EmailServiceTest {

    @InjectMocks
    EmailService emailService;

    @Mock
    EmailRepository emailRepository;

    @Test
    @DisplayName("It should send email")
    void it_should_send_email() {

        //when
        Email email = new Email();
        email.setToEmail("test@gmail.com");
        Mockito.when(emailRepository.save(Mockito.any())).thenReturn(email);

        //given
        EmailDto emailDto = new EmailDto();
        emailDto.setEmail("test@gmail.com");
        emailService.sendEmail(emailDto);

        //then
        verify(emailRepository, times(1)).save(Mockito.any());
    }
}
