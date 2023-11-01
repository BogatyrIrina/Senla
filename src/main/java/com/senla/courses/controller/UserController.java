package com.senla.courses.controller;

import com.senla.courses.dto.UserDto;
import com.senla.courses.mapper.UserMapper;
import com.senla.courses.service.UserService;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    public List<UserDto> users() {
        return userService.getAllUsers();
    }

    public UserDto getUserById(Long id) {
        return userService.getUserById(id);
    }

    public UserDto save(UserDto userDto) {
        return userService.createUser(userDto);
    }

    public UserDto update(UserDto updateUserDTO) {
        return userService.modifyUser(updateUserDTO);
    }

    public Map<String, Boolean> delete(UserDto userDto) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete",userService.delete(userDto));
        return response;
    }


}
