package com.senla.courses.security.impl;

import com.senla.courses.entity.User;
import com.senla.courses.exeption.UserNotFoundException;
import com.senla.courses.repository.UserRepository;
import com.senla.courses.security.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword())

                .roles(user.getRole().name())
                // .passwordEncoder(passwordEncoder::encode)
                .build();
    }
}
