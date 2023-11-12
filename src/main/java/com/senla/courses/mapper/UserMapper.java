package com.senla.courses.mapper;

import com.senla.courses.dto.UserDto;
import com.senla.courses.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface UserMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "userName", source = "name")
    @Mapping(target = "userEmail", source = "email")
    @Mapping(target = "password", source = "passwordHash")
    UserDto toDto(User user);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "userName")
    @Mapping(target = "email", source = "userEmail")
    @Mapping(target = "passwordHash", source = "password")
    User toEntity(UserDto userDto);
    default Collection<UserDto> toDtoList(Collection<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users) {
            if (user.getId() != null) {
                userDtoList.add(toDto(user));
            }
        }
        return userDtoList;
    }
}

