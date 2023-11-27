package com.senla.courses.repository;

import com.senla.courses.entity.Training;

import java.util.List;

public interface TrainingRepository {
    Training getTraining(Long id);
    List<Training> getAllTrainings();
    Training saveTraining(Training training);

    Training modifyTraining(Training training);
    boolean deleteTraining(Long id);
}
