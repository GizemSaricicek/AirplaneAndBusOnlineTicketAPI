package com.example.adminservice.dto;

public class AdminDto {
    private Integer id;
    private String name;
    private String surname;
    private String password;
    private String email;

    public AdminDto(Integer id, String name, String surname, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Integer getId() {
        return id;
    }
}
