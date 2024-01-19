package com.senla.courses.service;

import com.senla.courses.dto.TrainerDto;
import com.senla.courses.entity.Training;
import com.senla.courses.entity.User;

import java.util.Collection;
import java.util.List;

public interface TrainerService {
    TrainerDto createTrainer(TrainerDto trainer);

    TrainerDto getTrainerById(Long id);

    Collection<TrainerDto> getAllTrainers();

    TrainerDto modifyTrainer(TrainerDto trainer);
    boolean delete(Long id);

    List<User> getUsersByTrainer(Long trainerId);

    List<Training> getTrainingsByTrainer(Long trainerId);
}
