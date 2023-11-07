package com.senla.courses.service.impl;

import com.senla.courses.dto.*;
import com.senla.courses.entity.User;
import com.senla.courses.mapper.impl.UserMapperImpl;
import com.senla.courses.repository.UserRepository;
import com.senla.courses.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        User user = userRepository.getUser(id);
        if (user == null) {
            throw new IllegalArgumentException("Попытка получить не существующего пользователя с id=" + id);
        }
        return user;
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User createUser(User user) {
        return userRepository.saveUser(user);
    }

    @Override
    public User modifyUser(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User id can not be null");
        }
        return userRepository.modifyUser(user);
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User id can not be null");
        }

        return userRepository.deleteUser(id);
    }

//    @Override
//    public UserDto modifyUser(UserDto userDto) {
//        if (userDto.getId() == null) {
//            throw new IllegalArgumentException("User id can not be null");
//        }
//        User user = userMapper.toEntity(userDto);
//        User modifyUser = userRepository.saveUser(user);
//        return userMapper.toDto(modifyUser);
//    }
//
//    @Override
//    public boolean delete(UserDto userDto) {
//        User user = userMapper.toEntity(userDto);
//        try {
//            userRepository.deleteUser(user.getId());
//            return true;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public List<UserDto> GetAllUsersByTrainersAndTraining(UsersByTrainers usersByTrainers) {
//        return null;
//    }

}
