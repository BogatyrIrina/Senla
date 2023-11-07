package com.senla.courses.repository.impl;

import com.senla.courses.entity.User;
import com.senla.courses.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepositoryImpl implements UserRepository {
    private static final User user1 = new User();
    private static final User user2 = new User();
    private static final User user3 = new User();
    private static final User user4 = new User();

    private static final Map<Long, User> USERS = new HashMap<>();
    private static Long nextId = 1L;
    static {
        user1.setId(nextId++);
        user2.setId(nextId++);
        user3.setId(nextId++);
        user4.setId(nextId++);

        user1.setName("user1 Name");
        user2.setName("user2 Name");
        user3.setName("user3 Name");
        user4.setName("user4 Name");

        USERS.put(user1.getId(), user1);
        USERS.put(user2.getId(), user2);
        USERS.put(user3.getId(), user3);
        USERS.put(user4.getId(), user4);
    }

    public User getUser(Long id) {
        return USERS.get(id);
    }

    public Collection<User> getAllUsers() {
        return USERS.values();
    }

    public User saveUser(User user) {
        user.setId(nextId);
        USERS.put(user.getId(), user);
        return user;
    }

    public User modifyUser(User user) {
        USERS.put(user.getId(), user);
        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        return USERS.remove(id) != null;
    }

}
