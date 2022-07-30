package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.config.RabbitMqConfig;
import com.example.airplaneandbusonlineticketapi.dto.ConfigurationDto;
import com.example.airplaneandbusonlineticketapi.model.enums.ConfigurationType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitMqConfig rabbitMqConfig;

    public void sendEmail(ConfigurationDto configurationDto) {
        rabbitTemplate.convertAndSend(rabbitMqConfig.getQueueName(), configurationDto);
        // email değeri json tipine convert edilip kuyruğa yazılıyor.
    }
}
