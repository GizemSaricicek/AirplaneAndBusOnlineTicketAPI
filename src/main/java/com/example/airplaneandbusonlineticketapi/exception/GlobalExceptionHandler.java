package com.example.airplaneandbusonlineticketapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OnlineTicketAppException.class)
    public ResponseEntity<ExceptionResponse> handleOnlineTicketAppException(OnlineTicketAppException onlineTicketAppException){

        ExceptionResponse exceptionResponse = new ExceptionResponse(onlineTicketAppException.getMessage(), LocalDateTime.now());
        log.info("OnlineTicketAppException occurred:: response:{}",exceptionResponse.toString());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);

    }
}
