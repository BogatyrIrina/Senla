package com.senla.courses.service.impl;

import com.senla.courses.aop.Transaction;
import com.senla.courses.entity.User;
import com.senla.courses.repository.UserRepository;
import com.senla.courses.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        User user = userRepository.getById(id);
        if (user == null) {
            throw new IllegalArgumentException("Попытка получить не существующего пользователя с id=" + id);
        }
        return user;
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Transaction
    @Override
    public User createUser(User user) {
        return userRepository.saveUser(user);
    }

    @Transaction
    @Override
    public User modifyUser(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User id can not be null");
        }
        return userRepository.modifyUser(user);
    }

    @Transaction
    @Override
    public boolean delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User id can not be null");
        }

        return userRepository.deleteUser(id);
    }

}
