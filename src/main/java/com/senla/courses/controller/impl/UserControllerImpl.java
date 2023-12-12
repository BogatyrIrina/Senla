package com.senla.courses.controller.impl;

import com.senla.courses.controller.UserController;
import com.senla.courses.dto.UserDto;
import com.senla.courses.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

//@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @PostMapping
    public UserDto create(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @Override
    @GetMapping("/email/{email}")
    public UserDto getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping
    public Collection<UserDto> users() {
        return userService.getAllUsers();
    }

    @PutMapping
    public UserDto update(@RequestBody UserDto userDto) {
        return userService.modifyUser(userDto);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return userService.delete(id);
    }

}
