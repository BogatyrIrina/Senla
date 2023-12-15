package com.senla.courses.service;

import com.senla.courses.dto.TrainingDto;

import java.util.Collection;

public interface TrainingService {
    TrainingDto createTraining(TrainingDto trainingDto);
    TrainingDto getTrainingById(Long id);
    Collection<TrainingDto> getAllTrainings();
    TrainingDto modifyTraining(TrainingDto trainingDto);
    boolean delete(Long id);
}
