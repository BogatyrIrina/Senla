package com.senla.courses.controller;

import com.senla.courses.dto.TrainerDto;
import com.senla.courses.entity.Training;
import com.senla.courses.entity.User;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;

public interface TrainerController {
    TrainerDto create(TrainerDto trainerDto);
    TrainerDto getTrainerById(Long id);
    Collection<TrainerDto> trainers();
    TrainerDto update(TrainerDto trainerDto);
    boolean delete(Long id);
}
