package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.dto.PassengerDto;
import com.example.airplaneandbusonlineticketapi.exception.OnlineTicketAppException;
import com.example.airplaneandbusonlineticketapi.model.Passenger;
import com.example.airplaneandbusonlineticketapi.model.User;
import com.example.airplaneandbusonlineticketapi.model.enums.GenderType;
import com.example.airplaneandbusonlineticketapi.repository.PassengerRepository;
import com.example.airplaneandbusonlineticketapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private UserRepository userRepository;

    public Passenger createPassenger(User user,PassengerDto passengerDto) {
//        public Passenger createPassenger(User user, Integer voyageId, String name, String surname, GenderType gender, Integer age, String email, String phoneNumber) {

        Passenger passenger = new Passenger();
        passenger.setUser(user);
        passenger.setVoyageId(passengerDto.getVoyageId());
        passenger.setName(passengerDto.getName());
        passenger.setSurname(passengerDto.getSurname());
        passenger.setGender(passengerDto.getGender());
        passenger.setAge(passengerDto.getAge());
        passenger.setEmail(passengerDto.getEmail());
        passenger.setPhoneNumber(passengerDto.getPhoneNumber());
        user.getPassengerList().add(passenger);

        return passengerRepository.save(passenger);
    }
}
