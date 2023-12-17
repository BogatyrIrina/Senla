package com.senla.courses.filter;

import com.senla.courses.security.impl.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    private final UserDetailsServiceImpl userDetailsService;
    private final String secret;
    private final long validationTimeMillis;

    public JwtTokenProvider(UserDetailsServiceImpl userDetailsService,
                            @Value("${jwtSecretKey}") String secret,
                            @Value("${jwtValidationTime}") long validationTimeMillis) {
        this.userDetailsService = userDetailsService;
        this.secret = secret;
        this.validationTimeMillis = validationTimeMillis;
    }

    public String createToken(String login) {
    }

    public String resolveToken(HttpServletRequest request) {
    }

    public boolean validateToken(String token) {
    }

    public Authentication getAuthentication(String token) {
    }
}
