package com.senla.courses.repository;

import com.senla.courses.entity.Trainer;

import java.util.List;

public interface TrainerRepository {
    Trainer getTrainer(Long id);
    List<Trainer> getAllTrainers();
    Trainer saveTrainer(Trainer trainer);

    Trainer modifyTrainer(Trainer trainer);
    boolean deleteTrainer(Long id);
}
