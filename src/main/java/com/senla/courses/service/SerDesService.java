package com.senla.courses.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.courses.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
@RequiredArgsConstructor
public class SerDesService {
    private final ObjectMapper objectMapper;

    public String serialize(UserDto userDto) throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userDto);
    }

    public String serialize(Collection<UserDto> userDto) throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userDto);
    }

    public UserDto deSerialize(String s) throws JsonProcessingException {
        return objectMapper.readValue(s, UserDto.class);
    }
}
