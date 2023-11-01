package com.senla.courses.mapper;

import com.senla.courses.dto.UserDto;
import com.senla.courses.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;


public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
    List<UserDto> toDtoList(List<User> users);
}

