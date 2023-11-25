package com.senla.courses.controller;

import com.senla.courses.dto.TrainerDto;

import java.util.Collection;

public interface TrainerController {
    TrainerDto create(TrainerDto trainerDto);
    TrainerDto getTrainerById(Long id);
    Collection<TrainerDto> trainers();
    TrainerDto update(TrainerDto trainerDto);
    boolean delete(Long id);
}
