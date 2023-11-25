package com.senla.courses.service;

import com.senla.courses.entity.Trainer;

import java.util.Collection;

public interface TrainerService {
    Trainer createTrainer(Trainer trainer);
    Trainer getTrainerById(Long id);
    Collection<Trainer> getAllTrainers();
    Trainer modifyTrainer(Trainer trainer);
    boolean delete(Long id);
}
