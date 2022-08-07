package com.example.airplaneandbusonlineticketapi.configurationservice.service;

import com.example.airplaneandbusonlineticketapi.configurationservice.dto.TicketDto;
import com.example.airplaneandbusonlineticketapi.configurationservice.model.Message;
import com.example.airplaneandbusonlineticketapi.configurationservice.repository.MessageRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class MessageServiceTest {

    @InjectMocks
    private MessageService messageService;

    @Mock
    private MessageRepository messageRepository;

    @Test
    @DisplayName("It should send message")
    void it_should_send_message() {

        //when
        Message message = new Message();
        message.setToPhone("1111111111");
        Mockito.when(messageRepository.save(Mockito.any())).thenReturn(message);

        //given
        TicketDto messageTicketDto = new TicketDto();
        messageTicketDto.setPhoneNumber("1111111111");
        messageService.sendMessage(messageTicketDto);

        //then
        verify(messageRepository, times(1)).save(Mockito.any());
    }
}
