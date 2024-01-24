package com.senla.courses.service.impl;

import com.senla.courses.dto.TrainerDto;
import com.senla.courses.entity.Trainer;
import com.senla.courses.entity.Training;
import com.senla.courses.entity.User;
import com.senla.courses.exeption.TrainerNotFoundException;
import com.senla.courses.exeption.UserNotFoundException;
import com.senla.courses.mapper.TrainerMapper;
import com.senla.courses.repository.TrainerRepository;
import com.senla.courses.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;
    private final TrainerMapper trainerMapper;

    @Transactional
    @Override
    public TrainerDto createTrainer(TrainerDto trainerDto) {
        Trainer trainer = trainerMapper.toEntity(trainerDto);
        Trainer createdTrainer = trainerRepository.save(trainer);
        return trainerMapper.toDto(createdTrainer);
    }

    @Transactional
    @Override
    public TrainerDto getTrainerById(Long id) {
        Trainer trainer = trainerRepository.getById(id);
        if (trainer == null) {
            throw new TrainerNotFoundException("Trying to get a non-existing trainer with id = " + id);
        }
        return trainerMapper.toDto(trainer);
    }

    @Transactional
    @Override
    public Collection<TrainerDto> getAllTrainers() {
        return trainerMapper.toDtoList(trainerRepository.getAll());
    }

    @Transactional
    @Override
    public TrainerDto modifyTrainer(TrainerDto trainerDto) {
        if (trainerDto.getId() == null) {
            throw new  TrainerNotFoundException("Trainer id can not be null");
        }
        Trainer trainer = trainerRepository.getById(trainerDto.getId());
        if (trainer == null) {
            throw new RuntimeException("Trainer with id " + trainerDto.getId() + " not found");
        }

        trainer.setName(trainerDto.getTrainerName());
        trainer.setSpecialization(trainerDto.getSpecialization());
        Trainer updateTrainer = trainerRepository.update(trainer);

        return trainerMapper.toDto(updateTrainer);
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        if (id == null) {
            throw new TrainerNotFoundException("Trainer id can not be null");
        }
        Trainer trainer = trainerRepository.getById(id);
        if (trainer == null) {
            throw new TrainerNotFoundException("Trainer with id " + id + " not found");
        }
        return trainerRepository.delete(id);
    }

}
