package com.senla.courses.controller;

import com.senla.courses.dto.LoginDto;
import com.senla.courses.dto.RegistrationDto;
import com.senla.courses.dto.RegisterResponse;
import com.senla.courses.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/security")
@RequiredArgsConstructor
public class JwtAuthenticationController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDto authenticationRequest) {
        return ResponseEntity.ok(authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
    }


    @PostMapping(value = "/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegistrationDto registrationDto) {

        return ResponseEntity.ok(authService.register(registrationDto));
    }

}
