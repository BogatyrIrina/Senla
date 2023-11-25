package com.senla.courses.controller.impl;

import com.senla.courses.controller.TrainerController;
import com.senla.courses.dto.TrainerDto;
import com.senla.courses.entity.Trainer;
import com.senla.courses.mapper.TrainerMapper;
import com.senla.courses.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Collection;
@Controller
@RequiredArgsConstructor
public class TrainerControllerImpl implements TrainerController {
    private final TrainerService trainerService;
    private final TrainerMapper trainerMapper;
    @Override
    public TrainerDto create(TrainerDto trainerDto) {
        Trainer trainer = trainerMapper.toEntity(trainerDto);
        Trainer createdTrainer = trainerService.createTrainer(trainer);
        return trainerMapper.toDto(createdTrainer);
    }

    @Override
    public TrainerDto getTrainerById(Long id) {
        Trainer trainer = trainerService.getTrainerById(id);
        return trainerMapper.toDto(trainer);
    }

    @Override
    public Collection<TrainerDto> trainers() {
        Collection<Trainer> trainers = trainerService.getAllTrainers();
        return trainerMapper.toDtoList(trainers);
    }

    @Override
    public TrainerDto update(TrainerDto trainerDto) {
        Trainer trainer = trainerService.getTrainerById(trainerDto.getId());
        if (trainer == null) {
            throw new RuntimeException("Тренер с идентификатором " + trainerDto.getId()+ " не найден");
        }
        trainer.setName(trainerDto.getTrainerName());
        trainer.setSpecialization(trainerDto.getSpecialization());
        Trainer updateTrainer = trainerService.modifyTrainer(trainer);
        return trainerMapper.toDto(updateTrainer);
    }

    @Override
    public boolean delete(Long id) {
        Trainer trainer = trainerService.getTrainerById(id);
        if (trainer == null) {
            throw new RuntimeException("Тренер с идентификатором " + id + " не найден");
        }
        trainerService.delete(id);
        return true;
    }
}
