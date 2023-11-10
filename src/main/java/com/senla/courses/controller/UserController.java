package com.senla.courses.controller;

import com.senla.courses.dto.UserDto;
import com.senla.courses.entity.User;
import org.springframework.stereotype.Controller;

import java.util.Collection;

public interface UserController {
    UserDto create(UserDto userDto);
    UserDto getUserById(Long id);
    Collection<UserDto> users();
    UserDto update(UserDto userDto);
    boolean delete(Long id);
}

