package com.senla.courses.exeption;

public class TrainingNotFoundException extends RuntimeException{
    public TrainingNotFoundException(String message) {
        super(message);
    }
}
