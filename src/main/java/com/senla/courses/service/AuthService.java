package com.senla.courses.service;

import com.senla.courses.dto.JwtResponse;
import com.senla.courses.dto.RegistrationDto;
import com.senla.courses.dto.RegisterResponse;

public interface AuthService {
    JwtResponse login(String userName, String password);

    RegisterResponse register(RegistrationDto registrationDto);

}
