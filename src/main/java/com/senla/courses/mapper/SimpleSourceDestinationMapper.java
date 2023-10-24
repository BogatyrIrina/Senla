package com.senla.courses.mapper;

import com.senla.courses.dto.UserDto;
import com.senla.courses.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SimpleSourceDestinationMapper<Source, Destination> {
    Destination convertToDestination(Source source);
    Source convertToSource(Destination destination);
    List<UserDto> toDtoList(List<User> users);
}

