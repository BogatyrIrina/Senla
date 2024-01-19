package com.senla.courses.controller;

import com.senla.courses.dto.TrainingDto;
import com.senla.courses.dto.UserDto;

import java.util.Collection;
import java.util.List;

public interface UserController {
    UserDto create(UserDto userDto);
    UserDto getUserById(Long id);
    UserDto getUserByEmail(String email);
    Collection<UserDto> users();
    UserDto update(UserDto userDto);
    boolean delete(Long id);
    boolean addUserToTraining(Long userId, Long trainingId);
    boolean removeUserFromTraining(Long userId, Long trainingId);
    List<TrainingDto> getUserSchedule(Long userId);

}

