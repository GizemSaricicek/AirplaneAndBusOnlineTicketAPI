package com.example.airplaneandbusonlineticketapi.dto;

import com.example.airplaneandbusonlineticketapi.model.enums.ConfigurationType;
import org.springframework.amqp.core.Message;

public class ConfigurationDto{
    private EmailDto emailDto;
    private TicketDto ticketDto;
    private ConfigurationType configurationType;

    public EmailDto getEmailDto() {
        return emailDto;
    }

    public void setEmailDto(EmailDto emailDto) {
        this.emailDto = emailDto;
    }

    public TicketDto getTicketDto() {
        return ticketDto;
    }

    public void setTicketDto(TicketDto ticketDto) {
        this.ticketDto = ticketDto;
    }

    public ConfigurationType getConfigurationType() {
        return configurationType;
    }

    public void setConfigurationType(ConfigurationType configurationType) {
        this.configurationType = configurationType;
    }

    public ConfigurationDto(EmailDto emailDto) {
        this.emailDto = emailDto;
    }

    public ConfigurationDto() {
    }
}