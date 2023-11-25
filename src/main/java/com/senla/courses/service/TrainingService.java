package com.senla.courses.service;

import com.senla.courses.entity.Training;

import java.util.Collection;

public interface TrainingService {
    Training createTraining(Training training);
    Training getTrainingById(Long id);
    Collection<Training> getAllTrainings();
    Training modifyTraining(Training training);
    boolean delete(Long id);
}
