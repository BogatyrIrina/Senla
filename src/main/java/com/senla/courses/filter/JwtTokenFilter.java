package com.senla.courses.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


//@Component
//public class BasicAuthCorsFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(javax.servlet.http.HttpServletRequest httpServletRequest,
//                                    javax.servlet.http.HttpServletResponse httpServletResponse,
//                                    javax.servlet.FilterChain filterChain)
//            throws javax.servlet.ServletException, IOException {
//        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
//        filterChain.doFilter(httpServletRequest, httpServletResponse);
//    }
//}
@Component
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request, response);
        }
        catch (JwtAuthException e) {

        }
    }

}
