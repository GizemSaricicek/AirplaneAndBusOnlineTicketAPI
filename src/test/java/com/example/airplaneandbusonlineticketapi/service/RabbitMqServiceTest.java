package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.config.RabbitMqConfig;
import com.example.airplaneandbusonlineticketapi.dto.ConfigurationDto;
import com.example.airplaneandbusonlineticketapi.dto.EmailDto;
import com.example.airplaneandbusonlineticketapi.dto.UserDto;
import com.example.airplaneandbusonlineticketapi.model.User;
import com.example.airplaneandbusonlineticketapi.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class RabbitMqServiceTest {

//    @InjectMocks
//    private RabbitMqService rabbitMqService;
//
//    @Mock
//    private RabbitTemplate rabbitTemplate;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private RabbitMqConfig rabbitMqConfig;

//    @Test
//    @DisplayName("It should send email.")
//    void it_should_send_email() {
//
//        //when
//        String email = "test@gmail.com";
//        EmailDto emailDto = new EmailDto("test@gmail.com");
//        ConfigurationDto createConfigurationDto = new ConfigurationDto(emailDto);
//        UserDto userDto = new UserDto("testPw","test@gmail.com");
//        User user = new User();
//        user.setEmail(userDto.getEmail());
//        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
//
//        //given
//        EmailDto emailDtoGiven = new EmailDto("test@gmail.com");
//        ConfigurationDto configurationDtoGiven = new ConfigurationDto(emailDtoGiven);
//        rabbitTemplate.convertAndSend(Mockito.eq("airplaneAndBusTicketPlatform.configuration"), Mockito.any(ConfigurationDto.class));
//
//        //then
//        verify(rabbitTemplate, times(1)).convertAndSend(Mockito.eq("airplaneAndBusTicketPlatform.configuration"), Mockito.any(ConfigurationDto.class));
//    }
}
