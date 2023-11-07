package com.senla.courses.repository;

import com.senla.courses.entity.User;

import java.util.Collection;

public interface UserRepository {
    User getUser(Long id);
    Collection<User> getAllUsers();
    User saveUser(User user);

    User modifyUser(User user);
    boolean deleteUser(Long id);
}
