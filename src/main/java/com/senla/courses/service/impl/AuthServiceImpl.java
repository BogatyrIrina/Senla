package com.senla.courses.service.impl;

import com.senla.courses.dto.JwtResponse;
import com.senla.courses.dto.RegistrationDto;
import com.senla.courses.dto.RegisterResponse;
import com.senla.courses.dto.UserDto;
import com.senla.courses.exeption.RegistrationException;
import com.senla.courses.exeption.UnauthorisedException;
import com.senla.courses.filter.JwtTokenProvider;
import com.senla.courses.service.AuthService;
import com.senla.courses.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    @Override
    public JwtResponse login(String userName, String password) {
        authenticate(userName, password);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

        String token = jwtTokenProvider.createToken(userDetails.getUsername());

        return new JwtResponse(token);
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new UnauthorisedException("login failed");
        }
    }

    @Override
    public RegisterResponse register(RegistrationDto registrationDto) {
        try {
            UserDto userDto = new UserDto();
            userDto.setUserName(registrationDto.getName());
            userDto.setUserEmail(registrationDto.getEmail());
            //обновляем пароль на хэшированный
            userDto.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
            UserDto user = userService.createUser(userDto);
            RegisterResponse registerResponse = new RegisterResponse();
            registerResponse.setUsername(user.getId());
            return registerResponse;
        } catch (Exception e) {
            throw new RegistrationException("registration failed " + e.getMessage());
        }
    }
}
