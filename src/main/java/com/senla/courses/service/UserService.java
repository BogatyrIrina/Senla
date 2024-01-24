package com.senla.courses.service;

import com.senla.courses.dto.TrainingDto;
import com.senla.courses.dto.UserDto;

import java.util.Collection;
import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);
    Collection<UserDto> getAllUsers();
    UserDto modifyUser(UserDto userDto);
    boolean delete(Long id);
    UserDto getUserByEmail(String email);
    boolean addUserToTraining(Long userId, Long trainingId);
    boolean removeUserFromTraining(Long userId, Long trainingId);
    List<TrainingDto> getUserSchedule(Long userId);

}
