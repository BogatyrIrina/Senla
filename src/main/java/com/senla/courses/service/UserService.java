package com.senla.courses.service;

import com.senla.courses.dto.*;
import com.senla.courses.entity.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    Collection<User> getAllUsers();
    User modifyUser(User user);
    boolean delete(Long id);
//    List<UserDto> GetAllUsersByTrainersAndTraining(UsersByTrainers usersByTrainers);
}
