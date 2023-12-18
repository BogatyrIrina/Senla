package com.senla.courses.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtTokenProvider {
    private final UserDetailsService userDetailsService;
    private final String secret;
    private final long validationTimeMillis;

    public JwtTokenProvider(UserDetailsService userDetailsService,
                            @Value("${jwtSecretKey}") String secret,
                            @Value("${jwtValidationTime}") long validationTimeMillis) {
        this.userDetailsService = userDetailsService;
        this.secret = secret;
        this.validationTimeMillis = validationTimeMillis;
    }

    public String createToken(String login) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(login)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validationTimeMillis))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * достаем jwt токен из заголовка запроса
     *
     * @param request
     * @return jwt token
     */
    public String resolveToken(HttpServletRequest request) {
        final String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            return requestTokenHeader.substring(7);
        } else {
            throw new JwtAuthException("JWT Token does not begin with Bearer String");
        }
    }

    public boolean validateToken(String token) {
        String username = getUsernameFromToken(token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Authentication getAuthentication(String token) {
        String username = getUsernameFromToken(token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        return usernamePasswordAuthenticationToken;
    }

    private String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}
