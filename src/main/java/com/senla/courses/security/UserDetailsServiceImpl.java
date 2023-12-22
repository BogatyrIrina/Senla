package com.senla.courses.security;

import com.senla.courses.entity.Role;
import com.senla.courses.entity.User;
import com.senla.courses.exeption.UserNotFoundException;
import com.senla.courses.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        User user = Optional.ofNullable(userRepository.getById(Long.parseLong(username)))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String[] roles = user.getRoles().stream()
                .map(Role::getAuthority)
                .toArray(String[]::new);

        return org.springframework.security.core.userdetails.User.builder()
                //в качестве username используем id
                .username(String.valueOf(user.getId()))
                .password(user.getPassword())
                .roles(roles)
                .build();
    }
}
