package com.senla.courses.controller.impl;

import com.senla.courses.controller.UserController;
import com.senla.courses.dto.UserDto;
import com.senla.courses.entity.User;
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
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.createUser(user);
        return userMapper.toDto(createdUser);
    }

    public UserDto getUserById(Long id) {
        User user = userService.getUserById(id);
        return userMapper.toDto(user);
    }

    public Collection<UserDto> users() {
        Collection<User> users = userService.getAllUsers();
        return userMapper.toDtoList(users);
    }


    public UserDto update(UserDto userDto) {
        User user = userService.getUserById(userDto.getId());
        if (user == null) {
            throw new RuntimeException("Пользователь с идентификатором " + userDto.getId()+ " не найден");
        }
        user.setName(userDto.getUserName());
        user.setEmail(userDto.getUserEmail());
        User updateUser = userService.modifyUser(user);
        return userMapper.toDto(updateUser);
    }

    public boolean delete(Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new RuntimeException("Пользователь с идентификатором " + id + " не найден");
        }
        userService.delete(id);
        return true;
    }

}
