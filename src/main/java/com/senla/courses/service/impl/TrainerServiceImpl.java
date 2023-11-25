package com.senla.courses.service.impl;

import com.senla.courses.aop.Transaction;
import com.senla.courses.entity.Trainer;
import com.senla.courses.repository.TrainerRepository;
import com.senla.courses.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;

    @Transaction
    @Override
    public Trainer createTrainer(Trainer trainer) {
        return trainerRepository.saveTrainer(trainer);
    }

    @Override
    public Trainer getTrainerById(Long id) {
        Trainer trainer = trainerRepository.getTrainer(id);
        if (trainer == null) {
            throw new IllegalArgumentException("Попытка получить не существующего тренера с id=" + id);
        }
        return trainer;
    }

    @Override
    public Collection<Trainer> getAllTrainers() {
        return trainerRepository.getAllTrainers();
    }

    @Transaction
    @Override
    public Trainer modifyTrainer(Trainer trainer) {
        if (trainer.getId() == null) {
            throw new IllegalArgumentException("Trainer id can not be null");
        }
        return trainerRepository.modifyTrainer(trainer);
    }

    @Transaction
    @Override
    public boolean delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User id can not be null");
        }

        return trainerRepository.deleteTrainer(id);
    }
}
