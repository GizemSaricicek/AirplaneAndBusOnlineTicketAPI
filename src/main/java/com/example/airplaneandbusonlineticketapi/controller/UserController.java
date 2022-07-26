package com.example.airplaneandbusonlineticketapi.controller;

import com.example.airplaneandbusonlineticketapi.dto.IndividualUserDto;
import com.example.airplaneandbusonlineticketapi.model.IndividualUser;
import com.example.airplaneandbusonlineticketapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create_individual")
    public IndividualUser createIndividualUser(@RequestBody IndividualUserDto individualUserDto) {
        return userService.createIndividualUser(individualUserDto);
    }

//    @PostMapping("/create_corporate")
//    public CorporateUser createCorporateUser(@RequestBody CorporateUserDto cormporateUserDto) {
//        return userService.createCorporateUser(corporateUserDto);
//    }
}
