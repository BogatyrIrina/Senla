package com.senla.courses.controller.impl;

import com.senla.courses.controller.UserController;
import com.senla.courses.dto.UserDto;
import com.senla.courses.mapper.UserMapper;
import com.senla.courses.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserDto create(UserDto userDto) {
        return userService.createUser(userDto);
    }

    public UserDto getUserById(Long id) {
        return userService.getUserById(id);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    public Collection<UserDto> users() {
        return userService.getAllUsers();
    }


    public UserDto update(UserDto userDto) {
        return userService.modifyUser(userDto);
    }

    public boolean delete(Long id) {
        return userService.delete(id);
    }

}
