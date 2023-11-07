package com.senla.courses.mapper;

import com.senla.courses.dto.UserDto;
import com.senla.courses.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collection;

public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
    Collection<UserDto> toDtoList(Collection<User> users);
}

