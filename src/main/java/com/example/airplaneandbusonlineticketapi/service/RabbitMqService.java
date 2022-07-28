package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitMqConfig rabbitMqConfig;

    public void sendEmail(String email) {
        rabbitTemplate.convertAndSend(rabbitMqConfig.getQueueName(), email);
        // email değeri json tipine convert edilip kuyruğa yazılıyor.
    }
}
