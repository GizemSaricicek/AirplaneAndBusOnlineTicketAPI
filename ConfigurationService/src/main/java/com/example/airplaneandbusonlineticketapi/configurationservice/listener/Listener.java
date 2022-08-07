package com.example.airplaneandbusonlineticketapi.configurationservice.listener;

import com.example.airplaneandbusonlineticketapi.configurationservice.dto.ConfigurationDto;
import com.example.airplaneandbusonlineticketapi.configurationservice.dto.EmailDto;
import com.example.airplaneandbusonlineticketapi.configurationservice.dto.TicketDto;
import com.example.airplaneandbusonlineticketapi.configurationservice.model.enums.ConfigurationType;
import com.example.airplaneandbusonlineticketapi.configurationservice.service.EmailService;
import com.example.airplaneandbusonlineticketapi.configurationservice.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j //log atmak için
@Service
public class Listener implements ConfigurationListener {
    @Autowired
    EmailService emailService;

    @Autowired
    MessageService messageService;

    @RabbitListener(queues = "airplaneAndBusTicketPlatform.configuration")
    public void configListener(ConfigurationDto configurationDto) {
        log.info("configurationDto: {}", configurationDto.toString());
        ConfigurationType configurationType = configurationDto.getConfigurationType();

        if (configurationType.equals(ConfigurationType.EMAIL)) {

            EmailDto emailDto = configurationDto.getEmailDto();
            log.info("email address: {}", emailDto.getEmail());
            emailService.sendEmail(emailDto);
        }

        if (configurationType.equals(ConfigurationType.MESSAGE)) {

            TicketDto messageTicketDto = configurationDto.getTicketDto();
            log.info("message: {}", messageTicketDto.getPhoneNumber());
            messageService.sendMessage(messageTicketDto);
        }
    }
}
