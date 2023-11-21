package com.wheelcredit.Backend.dto;

import lombok.Data;

@Data
public class ClientDto {
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String description;
    private String image;
}
