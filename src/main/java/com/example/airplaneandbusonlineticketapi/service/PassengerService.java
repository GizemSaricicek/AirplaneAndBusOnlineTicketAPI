package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.dto.PassengerDto;
import com.example.airplaneandbusonlineticketapi.model.Passenger;
import com.example.airplaneandbusonlineticketapi.model.User;
import com.example.airplaneandbusonlineticketapi.repository.PassengerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger createPassenger(User user, PassengerDto passengerDto) {

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
