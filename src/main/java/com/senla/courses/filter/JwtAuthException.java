package com.senla.courses.filter;

public class JwtAuthException extends RuntimeException {
    public JwtAuthException(String message) {
        super(message);
    }

}
