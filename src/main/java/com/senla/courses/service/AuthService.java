package com.senla.courses.service;

import com.senla.courses.dto.Register;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(Register register);
}
