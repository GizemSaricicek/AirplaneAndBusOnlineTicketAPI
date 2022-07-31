package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.dto.ConfigurationDto;
import com.example.airplaneandbusonlineticketapi.dto.UserDto;
import com.example.airplaneandbusonlineticketapi.exception.OnlineTicketAppException;
import com.example.airplaneandbusonlineticketapi.model.User;
import com.example.airplaneandbusonlineticketapi.model.enums.UserType;
import com.example.airplaneandbusonlineticketapi.repository.UserRepository;
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
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private RabbitMqService rabbitMqService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Encryptor encryptor;

    private UserDto prepareUser() {
        UserDto userDto = new UserDto("testpw", "test@gmail.com");
        return userDto;
    }

    @Test
    @DisplayName("It should create user.")
    void it_should_create_user() {

        //when
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

    @Test
    @DisplayName("It should return user by email.")
    void it_should_return_user_by_email() {

        //when
        String email = "test@gmail.com";

        //given
        User responseUser = new User();
        UserDto userDto = prepareUser();
        responseUser.setEmail(userDto.getEmail());
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(responseUser));

        //then
        Optional<User> response = userRepository.findByEmail(email);
        verify(userRepository, times(1)).findByEmail(email);
        assertThat(response).isNotNull();
        assertThat(response.get().getEmail()).isEqualTo(responseUser.getEmail());
    }

    @Test
    @DisplayName("It should return user by email and password.")
    void it_should_return_user_by_email_and_password() {

        //when
        String email = "test@gmail.com";
        String password = "testPW";

        //given
        User responseUser = new User();
        UserDto userDto = prepareUser();
        responseUser.setEmail(userDto.getEmail());
        responseUser.setPassword(userDto.getPassword());
        Mockito.when(userRepository.findByEmailAndPassword(email, password)).thenReturn(Optional.of(responseUser));

        //then
        Optional<User> response = userRepository.findByEmailAndPassword(email, password);
        verify(userRepository, times(1)).findByEmailAndPassword(email, password);
        assertThat(response).isNotNull();
        assertThat(response.get().getEmail()).isEqualTo(responseUser.getEmail());
        assertThat(response.get().getPassword()).isEqualTo(responseUser.getPassword());

    }

    @Test
    @DisplayName("It should return encrypted password.")
    void it_should_return_password() {

        //when
        String password = "testpw";

        //given
        User responseUser = new User();
        UserDto userDto = prepareUser();
        responseUser.setPassword(userDto.getPassword());
        Mockito.when(encryptor.encryptGivenPassword(password)).thenReturn(String.valueOf(Optional.of(responseUser.getPassword())));

        //then
        boolean response = userRepository.findByPassword(encryptor.encryptGivenPassword(password)).isPresent();

        if (response) {
            String responsePassword = userRepository.findByPassword(encryptor.encryptGivenPassword(password)).get().getEmail();
            assertThat(responsePassword).isNotNull();
            assertThat(responsePassword).isEqualTo(responseUser.getPassword());
        }
        verify(userRepository, times(1)).findByPassword(encryptor.encryptGivenPassword(password));
    }

    @Test
    @DisplayName("It should throw OnlineTicketAppException when user already exist.")
    void it_should_throw_OnlineTicketAppException_when_user_already_exist() {

        OnlineTicketAppException expectedException = new OnlineTicketAppException("user already exist.");

        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenThrow(expectedException);

        Throwable exception = catchThrowable(() -> userRepository.findByEmail(Mockito.anyString()));

        assertThat(exception).isInstanceOf(OnlineTicketAppException.class);
        assertThat(exception.getMessage()).isEqualTo(expectedException.getMessage());

    }
}
