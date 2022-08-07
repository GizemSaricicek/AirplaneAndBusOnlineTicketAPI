package com.example.airplaneandbusonlineticketapi.configurationservice.service;

import com.example.airplaneandbusonlineticketapi.configurationservice.dto.EmailDto;
import com.example.airplaneandbusonlineticketapi.configurationservice.model.Email;
import com.example.airplaneandbusonlineticketapi.configurationservice.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    public void sendEmail(EmailDto emailDto) {
        Email email = new Email();
        email.setToEmail(emailDto.getEmail());
        email.setTitle(emailDto.getTitle());
        email.setEmailMessage(emailDto.getContent());

        emailRepository.save(email);
    }
}
