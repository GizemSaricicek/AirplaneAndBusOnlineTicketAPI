package com.example.airplaneandbusonlineticketapi.service;

import org.springframework.amqp.core.AmqpTemplate;
import com.example.airplaneandbusonlineticketapi.dto.ConfigurationDto;
import com.example.airplaneandbusonlineticketapi.dto.UserDto;
import com.example.airplaneandbusonlineticketapi.model.User;
import com.example.airplaneandbusonlineticketapi.model.enums.UserType;
import com.example.airplaneandbusonlineticketapi.repository.UserRepository;
import com.example.airplaneandbusonlineticketapi.security.Encryptor;
import org.junit.jupiter.api.BeforeEach;
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
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private RabbitMqService rabbitMqService;

    @Mock
    private  AmqpTemplate rabbitTemplate;
    @Mock
    private UserRepository userRepository;

    @Mock
    private Encryptor encryptor;

//    private UserDto prepareUser() {
//        UserDto userDto = new UserDto("email@gmail.com", "password");
//        return userDto;
//    }

    @BeforeEach
    void init() { // tüm testlerde kullanılacak mock'lar burada tanımlanabilir.
//        Mockito.when(userRepository.save(Mockito.any())).thenReturn(null);
    }

    @Test
    @DisplayName("It should create user.")
    void it_should_create_user() {

//        when
        User createUser = new User(1, "testName", "testpw", UserType.INDIVIDUAL, "test@gmail.com", "1111111111");
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(createUser);

        //given

        UserDto userDto = new UserDto("testpw", "test@gmail.com");
        User responseUser = userService.createUser(userDto);

        //then

        //userRepository herhangi bir obje ile çağrılabilir.
        //save metodu çağrılabilmiş mi verify ediliyor.
        verify(userRepository, times(1)).save(Mockito.any());
        verify(rabbitMqService, times(1)).sendEmail(Mockito.any(ConfigurationDto.class));

        //gelen değer istenilen değer mi verify ediliyor.
        assertThat(responseUser.getEmail()).isEqualTo(userDto.getEmail());
        assertThat(responseUser.getPassword()).isEqualTo(userDto.getPassword());

    }
}
