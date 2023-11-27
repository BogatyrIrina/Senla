package com.senla.courses.service.impl;

import com.senla.courses.entity.Training;
import com.senla.courses.repository.TrainingRepository;
import com.senla.courses.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    @Override
    public Training createTraining(Training training) {
        return trainingRepository.saveTraining(training);
    }

    @Override
    public Training getTrainingById(Long id) {
        Training training = trainingRepository.getTraining(id);
        if (training == null) {
            throw new IllegalArgumentException("Попытка получить не существующу тренировку пользователя с id=" + id);
        }
        return training;
    }

    @Override
    public Collection<Training> getAllTrainings() {
        return trainingRepository.getAllTrainings();
    }

    @Override
    public Training modifyTraining(Training training) {
        if (training.getId() == null) {
            throw new IllegalArgumentException("Training id can not be null");
        }
        return trainingRepository.modifyTraining(training);
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Training id can not be null");
        }
        return trainingRepository.deleteTraining(id);
    }
}
