package com.senla.courses.repository;

import com.senla.courses.entity.User;
import com.senla.courses.repository.impl.AbstractCrudDao;

import java.util.Collection;

public class UserDao extends AbstractCrudDao<User> implements UserRepository{

    @Override
    public Collection<User> getAll() {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
