package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.config.RabbitMqConfig;
import com.example.airplaneandbusonlineticketapi.dto.ConfigurationDto;
import com.example.airplaneandbusonlineticketapi.dto.EmailDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class RabbitMqServiceTest {

    @InjectMocks
    private RabbitMqService rabbitMqService;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private RabbitMqConfig rabbitMqConfig;

    @Test
    @DisplayName("It should send email.")
    void it_should_send_email() {
//
//        //when
//        EmailDto emailDto = new EmailDto("test@gmail.com");
//        ConfigurationDto createConfigurationDto = new ConfigurationDto(emailDto);
//        Mockito.when(rabbitMqService.sendEmail(Mockito.any())).thenReturn(createConfigurationDto.getEmailDto().getEmail());
//
//        //given
//        EmailDto emailDtoGiven = new EmailDto("test@gmail.com");
//        ConfigurationDto configurationDtoGiven = new ConfigurationDto(emailDtoGiven);
//        String responseEmail = rabbitMqService.sendEmail(Mockito.any());
//
//        //then
//
//        //userRepository herhangi bir obje ile çağrılabilir.
//        //save metodu çağrılabilmiş mi verify ediliyor.
//        verify(rabbitMqService, times(1)).sendEmail(Mockito.any());
//        verify(rabbitTemplate, times(1)).convertAndSend(Mockito.any(ConfigurationDto.class));
//
//        //gelen değer istenilen değer mi verify ediliyor.
//        assertThat(responseEmail).isEqualTo(emailDtoGiven.getEmail());
    }
}
