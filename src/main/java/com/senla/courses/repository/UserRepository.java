package com.senla.courses.repository;

import com.senla.courses.entity.User;

public interface UserRepository extends GenericRepository<User, Long>{
    User getByEmail(String email);
}
