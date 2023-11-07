package com.senla.courses.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.courses.dto.UserDto;
import com.senla.courses.mapper.UserMapper;
import com.senla.courses.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public String create(UserDto userDto) {
        UserDto response = userMapper.toDto(userService.createUser(userMapper.toEntity(userDto)));

        return objectMapper.writeValueAsString(response);
    }

    @SneakyThrows
    public String users() {
        Collection<UserDto> response = userMapper.toDtoList(userService.getAllUsers());

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
    }

    @SneakyThrows
    public String getUserById(Long id) {
        UserDto response = userMapper.toDto(userService.getUserById(id));

        return objectMapper.writeValueAsString(response);
    }

    @SneakyThrows
    public String update(UserDto userDto) {
        UserDto response = userMapper.toDto(userService.modifyUser(userMapper.toEntity(userDto)));

        return objectMapper.writeValueAsString(response);
    }

    @SneakyThrows
    public String delete(Long id) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", userService.delete(id));
        return objectMapper.writeValueAsString(response);
    }
}
