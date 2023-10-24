package com.senla.courses.repository.impl;

import com.senla.courses.entity.User;
import com.senla.courses.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {
    private static final User user1 = new User();
    private static final User user2 = new User();
    private static final User user3 = new User();
    private static final User user4 = new User();

    private static final List<User> userList = new ArrayList<>();
    private Long nextId = 1L;
    public UserRepositoryImpl(){
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
    }

    public User getUser(Long id) {
        return userList.stream().filter(user -> user.getId()
                .equals(id))
                .toList().get(0);
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public Long saveUser(User user) {
        user.setId(nextId);
        userList.add(user);
        nextId++;
        return user.getId();
    }

    public User modifyUser(User user, Long id) {
        User existingUser = userList.stream().
                filter(user5 -> user5.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (existingUser != null){
            existingUser.setName(user.getName());
            existingUser.setPasswordHash(user.getPasswordHash());
        }
        return existingUser;
    }

    @Override
    public void deleteUser(Long id) {
        userList.removeIf(user -> user.getId().equals(id));
    }

//    public void deleteUser(User user) {
//        userList.remove(user);
//    }
}
