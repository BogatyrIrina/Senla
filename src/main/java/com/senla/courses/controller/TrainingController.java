package com.senla.courses.controller;

import com.senla.courses.dto.TrainingDto;

import java.util.Collection;

public interface TrainingController {
    TrainingDto create(TrainingDto trainingDto);
    TrainingDto getTrainingById(Long id);
    Collection<TrainingDto> trainings();
    TrainingDto update(TrainingDto trainingDto);
    boolean delete(Long id);
}
