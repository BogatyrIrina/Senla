package com.senla.courses.service;

import com.senla.courses.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto createUser(UserDto userDto);
    UserDto modifyUser(UserDto userDto);
    boolean delete(UserDto userDto);
    List<UserDto> GetAllUsersByTrainersAndTraining(UsersByTrainers usersByTrainers);
}
