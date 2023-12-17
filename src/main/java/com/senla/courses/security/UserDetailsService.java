package com.senla.courses.security;

import com.senla.courses.exeption.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UserNotFoundException;
}
