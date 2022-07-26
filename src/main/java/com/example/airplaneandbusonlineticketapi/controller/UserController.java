package com.example.airplaneandbusonlineticketapi.controller;

import com.example.airplaneandbusonlineticketapi.dto.CorporateUserDto;
import com.example.airplaneandbusonlineticketapi.dto.IndividualUserDto;
import com.example.airplaneandbusonlineticketapi.model.CorporateUser;
import com.example.airplaneandbusonlineticketapi.model.IndividualUser;
import com.example.airplaneandbusonlineticketapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create_individual")
    public IndividualUser createIndividualUser(@RequestBody IndividualUserDto individualUserDto) {
        return userService.createIndividualUser(individualUserDto);
    }

    @PostMapping("/create_corporate")
    public CorporateUser createCorporateUser(@RequestBody CorporateUserDto corporateUserDto) {
        return userService.createCorporateUser(corporateUserDto);
    }

    @GetMapping("/login_individual")
    public IndividualUser loginIndividualUser(@RequestBody IndividualUserDto individualUserDto){
        return userService.loginIndividualUser(individualUserDto);
    }

    @GetMapping("/login_corporate")
    public CorporateUser loginCorporateUser(@RequestBody CorporateUserDto corporateUserDto){
        return userService.loginCorporateUser(corporateUserDto);
    }
}
