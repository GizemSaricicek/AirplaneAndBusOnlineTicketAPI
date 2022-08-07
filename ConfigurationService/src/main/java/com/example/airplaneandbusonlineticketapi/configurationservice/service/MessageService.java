package com.example.airplaneandbusonlineticketapi.configurationservice.service;

import com.example.airplaneandbusonlineticketapi.configurationservice.dto.TicketDto;
import com.example.airplaneandbusonlineticketapi.configurationservice.model.Message;
import com.example.airplaneandbusonlineticketapi.configurationservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void sendMessage(TicketDto ticketDto) {

        Message message = new Message();
        message.setName(ticketDto.getName());
        message.setTitle("Bilgilendirme Mesajı");
        message.setSurname(ticketDto.getSurname());
        message.setToPhone(ticketDto.getPhoneNumber());
        message.setAmount(ticketDto.getAmount());
        message.setCurrencyType(ticketDto.getCurrencyType());
        message.setContent("Bilet Ödemesi Alınmıştır.");

        messageRepository.save(message);
    }
}
