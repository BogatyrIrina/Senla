package com.senla.courses.repository;

import com.senla.courses.entity.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User, Long>{
    User getByEmail(String email);

    User findByUsername(String username);
}
