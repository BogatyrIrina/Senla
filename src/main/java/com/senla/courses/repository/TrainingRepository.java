package com.senla.courses.repository;

import com.senla.courses.entity.Training;

import java.util.List;

public interface TrainingRepository {
    Training getTraining(Long id);
    List<Training> getAllTraining();
    Long saveTraining(Training training);

    Training modifyTraining(Training training, Long id);
    void deleteTraining(Long id);
}
