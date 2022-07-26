package com.example.airplaneandbusonlineticketapi.controller;

import com.example.airplaneandbusonlineticketapi.dto.AdminDto;
import com.example.airplaneandbusonlineticketapi.model.Admin;
import com.example.airplaneandbusonlineticketapi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admins")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping
    public Admin createAdmin(@RequestBody AdminDto adminDto) {
        return adminService.createAdmin(adminDto);
    }
}
