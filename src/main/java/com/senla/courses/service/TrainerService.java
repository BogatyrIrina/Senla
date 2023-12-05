package com.senla.courses.service;

import com.senla.courses.dto.TrainerDto;

import java.util.Collection;

public interface TrainerService {
    TrainerDto createTrainer(TrainerDto trainer);

    TrainerDto getTrainerById(Long id);

    Collection<TrainerDto> getAllTrainers();

    TrainerDto modifyTrainer(TrainerDto trainer);
    boolean delete(Long id);
}
