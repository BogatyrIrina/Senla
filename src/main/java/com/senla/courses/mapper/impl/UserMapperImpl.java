package com.senla.courses.mapper.impl;

import com.senla.courses.dto.UserDto;
import com.senla.courses.entity.User;
import com.senla.courses.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getName());
        userDto.setId(user.getId());
        userDto.setPassword(user.getPasswordHash());
        return userDto;
    }

    @Override
    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getUserName());
        user.setPasswordHash(userDto.getPassword());
        return user;
    }

    @Override
    public Collection<UserDto> toDtoList(Collection<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users) {
            if (user.getId() != null) {
                userDtoList.add(toDto(user));
            }
        }
        return userDtoList;
    }
}
