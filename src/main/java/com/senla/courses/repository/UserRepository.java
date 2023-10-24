package com.senla.courses.repository;

import com.senla.courses.entity.User;

import java.util.List;

public interface UserRepository {
    User getUser(Long id);
    List<User> getAllUsers();
    Long saveUser(User user);

    User modifyUser(User user, Long id);
    void deleteUser(Long id);
}
