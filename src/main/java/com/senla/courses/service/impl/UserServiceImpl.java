package com.senla.courses.service.impl;

import com.senla.courses.dto.*;
import com.senla.courses.entity.User;
import com.senla.courses.mapper.UserMapper;
import com.senla.courses.mapper.UserMapperImpl;
import com.senla.courses.repository.UserRepository;
import com.senla.courses.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapperImpl userMapper;

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.getUser(id);
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.getAllUsers();
        return userMapper.toDtoList(users);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.saveUser(user);
        return userMapper.toDto(savedUser);
    }
    
    @Override
    public UserDto modifyUser(UserDto userDto) {
        if (userDto.getId() == null) {
            throw new IllegalArgumentException("User id can not be null");
        }
        User user = userMapper.toEntity(userDto);
        User modifyUser = userRepository.saveUser(user);
        return userMapper.toDto(modifyUser);
    }

    @Override
    public boolean delete(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        try {
            userRepository.deleteUser(user.getId());
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserDto> GetAllUsersByTrainersAndTraining(UsersByTrainers usersByTrainers) {
        return null;
    }

}
