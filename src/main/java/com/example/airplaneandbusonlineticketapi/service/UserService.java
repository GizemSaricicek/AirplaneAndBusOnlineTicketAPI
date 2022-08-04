package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.dto.ConfigurationDto;
import com.example.airplaneandbusonlineticketapi.dto.EmailDto;
import com.example.airplaneandbusonlineticketapi.dto.UserDto;
import com.example.airplaneandbusonlineticketapi.exception.OnlineTicketAppException;
import com.example.airplaneandbusonlineticketapi.model.User;
import com.example.airplaneandbusonlineticketapi.model.enums.ConfigurationType;
import com.example.airplaneandbusonlineticketapi.repository.UserRepository;
import com.example.airplaneandbusonlineticketapi.security.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Encryptor encryptor;
    @Autowired
    private RabbitMqService rabbitMqService;

    public User createUser(UserDto userDto) {
        boolean isExists = userRepository.findByEmail(userDto.getEmail()).isPresent();
        if (isExists) {
            throw new OnlineTicketAppException("User already exists.");
        }

        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encryptor.encryptGivenPassword(userDto.getPassword()));
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setUserType(userDto.getUserType());

        EmailDto emailDto = new EmailDto(userDto.getEmail());

        ConfigurationDto configurationDto = new ConfigurationDto();
        configurationDto.setEmailDto(emailDto);
        configurationDto.setConfigurationType(ConfigurationType.EMAIL);

        rabbitMqService.sendConfiguration(configurationDto);
        return userRepository.save(user);
    }

    public User loginUser(UserDto userDto) {
        String inputUserPassword = encryptor.encryptGivenPassword(userDto.getPassword());
        User foundUser = userRepository.findByEmailAndPassword(userDto.getEmail(), inputUserPassword).orElseThrow(() -> new OnlineTicketAppException("User not found."));
        return foundUser;
    }
}
