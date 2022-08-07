package com.example.airplaneandbusonlineticketapi.configurationservice.dto;

import com.example.airplaneandbusonlineticketapi.configurationservice.model.enums.ConfigurationType;

public class ConfigurationDto {

    private EmailDto emailDto;
    private TicketDto ticketDto;
    private ConfigurationType configurationType;

    public EmailDto getEmailDto() {
        return emailDto;
    }

    public TicketDto getTicketDto() {
        return ticketDto;
    }

    public ConfigurationType getConfigurationType() {
        return configurationType;
    }

    public ConfigurationDto() {
    }

    @Override
    public String toString() {
        return "ConfigurationDto{" +
                "emailDto=" + emailDto +
                ", ticketDto=" + ticketDto +
                ", configurationType=" + configurationType +
                '}';
    }
}
