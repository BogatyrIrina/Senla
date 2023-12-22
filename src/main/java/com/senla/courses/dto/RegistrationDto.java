package com.senla.courses.dto;

import lombok.Data;

@Data
public class RegistrationDto {
    private String name;
    private String surname;
    private String password;
    private String email;
    private String role;
}
