package com.senla.courses.service.impl;

import com.senla.courses.dto.UserDto;
import com.senla.courses.entity.User;
import com.senla.courses.exeption.UserNotFoundException;
import com.senla.courses.mapper.UserMapper;
import com.senla.courses.repository.UserRepository;
import com.senla.courses.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.getById(id);
        if (user == null) {
            throw new UserNotFoundException("Попытка получить не существующего пользователя с id= [" + id + "]");
        }
        return userMapper.toDto(user);
    }

    @Transactional
    @Override
    public Collection<UserDto> getAllUsers() {
        var users = userRepository.getAll();
        for (User user : users) {
            System.out.println(user.getTrainers());
        }
        return userMapper.toDtoList(users);
    }

    @Transactional
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User cseatedUser = userRepository.save(user);
        return userMapper.toDto(cseatedUser);
    }

    @Transactional
    @Override
    public UserDto modifyUser(UserDto userDto) {

        User user = userRepository.getById(userDto.getId());

        if (user == null) {
            throw new RuntimeException("Пользователь с идентификатором " + userDto.getId() + " не найден");
        }
        user.setName(userDto.getUserName());
        user.setEmail(userDto.getUserEmail());

        User updateUser = userRepository.update(user);

        return userMapper.toDto(updateUser);
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User id can not be null");
        }
        User user = userRepository.getById(id);
        if (user == null) {
            throw new RuntimeException("Пользователь с идентификатором " + id + " не найден");
        }
        return userRepository.delete(id);
    }
    @Transactional
    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.getByEmail(email);
        if (user == null) {
            throw new RuntimeException("Пользователь с email " + email + " не найден");
        }
        return userMapper.toDto(user);
    }

}
