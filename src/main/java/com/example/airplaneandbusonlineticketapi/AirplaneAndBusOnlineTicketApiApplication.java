package com.example.airplaneandbusonlineticketapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AirplaneAndBusOnlineTicketApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirplaneAndBusOnlineTicketApiApplication.class, args);
    }

}
