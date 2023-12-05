package com.senla.courses.controller.impl;

import com.senla.courses.controller.TrainerController;
import com.senla.courses.dto.TrainerDto;
import com.senla.courses.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Collection;
@Controller
@RequiredArgsConstructor
public class TrainerControllerImpl implements TrainerController {
    private final TrainerService trainerService;
    @Override
    public TrainerDto create(TrainerDto trainerDto) {
        return trainerService.createTrainer(trainerDto);
    }

    @Override
    public TrainerDto getTrainerById(Long id) {
        return trainerService.getTrainerById(id);
    }

    @Override
    public Collection<TrainerDto> trainers() {
        return trainerService.getAllTrainers();
    }

    @Override
    public TrainerDto update(TrainerDto trainerDto) {
        return trainerService.modifyTrainer(trainerDto);
    }

    @Override
    public boolean delete(Long id) {
        return trainerService.delete(id);
    }
}
