package com.senla.courses.mapper;

import com.senla.courses.dto.UserDto;
import com.senla.courses.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper implements SimpleSourceDestinationMapper<User, UserDto> {

    @Override
    public UserDto convertToDestination(User source) {
        if (source == null) {
            return null;
        }

        UserDto destination = new UserDto();
        destination.setUserName(source.getName());
        destination.setPassword(source.getPasswordHash());
        return destination;
    }

    @Override
    public User convertToSource(UserDto destination) {
        if (destination == null) {
            return null;
        }

        User source = new User();
        source.setName(destination.getUserName());
        source.setPasswordHash(destination.getPassword());
        return source;
    }

    public List<UserDto> toDtoList(List<User> users) {
        return users.stream()
                .map(this::convertToDestination)
                .collect(Collectors.toList());
    }

//    @Mapper(componentModel = "spring")
//    public interface SimpleSourceDestinationMapper {
//
//        EntityDto toDto(Entity entity);
//
//        Entity toEntity(EntityDto entityDto);
//    }
}

