package com.senla.courses.service.impl;

import com.senla.courses.entity.Training;
import com.senla.courses.repository.TrainingRepository;
import com.senla.courses.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
   @Transactional
    @Override
    public Training createTraining(Training training) {
        return trainingRepository.save(training);
    }

    @Transactional
    @Override
    public Training getTrainingById(Long id) {
        Training training = trainingRepository.getById(id);
        if (training == null) {
            throw new IllegalArgumentException("Попытка получить не существующу тренировку пользователя с id=" + id);
        }
        return training;
    }

    @Override
    public Collection<Training> getAllTrainings() {
        return trainingRepository.getAll();
    }

    @Transactional
    @Override
    public Training modifyTraining(Training training) {
        if (training.getId() == null) {
            throw new IllegalArgumentException("Training id can not be null");
        }
        return trainingRepository.update(training);
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Training id can not be null");
        }
        return trainingRepository.delete(id);
    }
}
