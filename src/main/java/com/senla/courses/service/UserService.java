package com.senla.courses.service;

import com.senla.courses.dto.UserDto;

import java.util.Collection;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);
    Collection<UserDto> getAllUsers();
    UserDto modifyUser(UserDto userDto);
    boolean delete(Long id);
    UserDto getUserByEmail(String email);
}
