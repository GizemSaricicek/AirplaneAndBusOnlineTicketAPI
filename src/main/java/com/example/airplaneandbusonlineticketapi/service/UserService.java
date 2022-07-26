package com.example.airplaneandbusonlineticketapi.service;

import com.example.airplaneandbusonlineticketapi.dto.CorporateUserDto;
import com.example.airplaneandbusonlineticketapi.dto.IndividualUserDto;
import com.example.airplaneandbusonlineticketapi.exception.UserAlreadyExistsException;
import com.example.airplaneandbusonlineticketapi.exception.UserNotFoundException;
import com.example.airplaneandbusonlineticketapi.model.CorporateUser;
import com.example.airplaneandbusonlineticketapi.model.IndividualUser;
import com.example.airplaneandbusonlineticketapi.repository.CorporateUserRepository;
import com.example.airplaneandbusonlineticketapi.repository.IndividualUserRepository;
import com.example.airplaneandbusonlineticketapi.security.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    IndividualUserRepository individualUserRepository;
    @Autowired
    CorporateUserRepository corporateUserRepository;
    @Autowired
    Encryptor encryptor;

    public IndividualUser createIndividualUser(IndividualUserDto individualUserDto) {

        boolean isExists = individualUserRepository.findByEmail(individualUserDto.getEmail()).isPresent();
        if (isExists) {
            throw new UserAlreadyExistsException();
        }

        IndividualUser individualUser = new IndividualUser();
        individualUser.setName(individualUserDto.getName());
        individualUser.setSurname(individualUserDto.getSurname());
        individualUser.setEmail(individualUserDto.getEmail());
        individualUser.setPassword(encryptor.encryptGivenPassword(individualUserDto.getPassword()));
        individualUser.setPhoneNumber(individualUserDto.getPhoneNumber());

        individualUserRepository.save(individualUser);
        return individualUser;
    }

    public CorporateUser createCorporateUser(CorporateUserDto corporateUserDto) {

        boolean isExists = corporateUserRepository.findByEmail(corporateUserDto.getEmail()).isPresent();
        if (isExists) {
            throw new UserAlreadyExistsException();
        }

        CorporateUser corporateUser = new CorporateUser();
        corporateUser.setName(corporateUserDto.getName());
        corporateUser.setEmail(corporateUserDto.getEmail());
        corporateUser.setPassword(encryptor.encryptGivenPassword(corporateUserDto.getPassword()));
        corporateUser.setPhoneNumber(corporateUserDto.getPhoneNumber());

        corporateUserRepository.save(corporateUser);
        return corporateUser;
    }

    public IndividualUser loginIndividualUser(IndividualUserDto individualUserDto) {

        String inputPassword = encryptor.encryptGivenPassword(individualUserDto.getPassword());
        IndividualUser found = individualUserRepository.findByEmailAndPassword(individualUserDto.getEmail(), inputPassword).orElseThrow(() -> new UserNotFoundException());
        return found;
    }

    public CorporateUser loginCorporateUser(CorporateUserDto corporateUserDto) {
        String inputCorporatePassword = encryptor.encryptGivenPassword(corporateUserDto.getPassword());
        CorporateUser found = corporateUserRepository.findByEmailAndPassword(corporateUserDto.getEmail(), inputCorporatePassword).orElseThrow(() -> new UserNotFoundException());
        return found;
    }

}
