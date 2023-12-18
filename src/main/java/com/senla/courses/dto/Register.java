package com.senla.courses.dto;

import lombok.Data;

@Data
public class Register {
    private String name;
    private String surname;
    private String password;
    private String email;
    private String role;
}
