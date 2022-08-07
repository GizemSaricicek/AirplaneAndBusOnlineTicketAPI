package com.example.airplaneandbusonlineticketapi.configurationservice.dto;

public class EmailDto {
    private final String title = "Hoşgeldiniz.";
    private final String content = "Üyeliğiniz başarı ile gerçekleştirilmiştir.";
    private String email;

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
