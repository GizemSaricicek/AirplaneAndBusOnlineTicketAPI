package com.example.adminservice.controller;

import com.example.adminservice.dto.AdminDto;
import com.example.adminservice.model.Admin;
import com.example.adminservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping
    public Admin createAdmin(@RequestBody AdminDto adminDto) {
        return adminService.createAdmin(adminDto);
    }

    @GetMapping("/login")
    public Admin loginAdmin(@RequestBody AdminDto adminDto) {
        return adminService.loginAdmin(adminDto);
    }
}
