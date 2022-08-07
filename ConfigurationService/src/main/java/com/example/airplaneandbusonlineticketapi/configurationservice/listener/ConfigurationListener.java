package com.example.airplaneandbusonlineticketapi.configurationservice.listener;

import com.example.airplaneandbusonlineticketapi.configurationservice.dto.ConfigurationDto;
import org.springframework.stereotype.Service;

@Service
public interface ConfigurationListener {
    public void configListener(ConfigurationDto configurationDto);
}
