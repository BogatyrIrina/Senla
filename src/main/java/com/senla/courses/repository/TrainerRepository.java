package com.senla.courses.repository;

import com.senla.courses.entity.Trainer;
import com.senla.courses.entity.User;

import java.util.List;

public interface TrainerRepository {
    Trainer getTrainer(Long id);
    List<Trainer> getAllTrainers();
    Long saveTrainer(Trainer trainer);

    Trainer modifyTrainer(Trainer trainer, Long id);
    void deleteTrainer(Long id);
}
