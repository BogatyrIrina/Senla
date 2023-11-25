package com.senla.courses.controller.impl;

import com.senla.courses.controller.TrainingController;
import com.senla.courses.dto.TrainingDto;
import com.senla.courses.entity.Training;
import com.senla.courses.mapper.TrainingMapper;
import com.senla.courses.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
@RequiredArgsConstructor
public class TrainingControllerImpl implements TrainingController {
    private final TrainingService trainingService;
    private final TrainingMapper trainingMapper;
    @Override
    public TrainingDto create(TrainingDto trainingDto) {
        Training training = trainingMapper.toEntity(trainingDto);
        Training createdTraining = trainingService.createTraining(training);
        return trainingMapper.toDto(createdTraining);
    }

    @Override
    public TrainingDto getTrainingById(Long id) {
        Training training = trainingService.getTrainingById(id);
        return trainingMapper.toDto(training);
    }

    @Override
    public Collection<TrainingDto> trainings() {
        Collection<Training> trainings = trainingService.getAllTrainings();
        return trainingMapper.toDtoList(trainings);
    }

    @Override
    public TrainingDto update(TrainingDto trainingDto) {
        Training training = trainingService.getTrainingById(trainingDto.getId());
        if (training== null) {
            throw new RuntimeException("Тренировка с идентификатором " + trainingDto.getId()+ " не найденa");
        }
        training.setName(trainingDto.getTrainingName());
        training.setTime(trainingDto.getTrainingTime());
        Training updateTraining = trainingService.modifyTraining(training);
        return trainingMapper.toDto(updateTraining);
    }

    @Override
    public boolean delete(Long id) {
        Training training = trainingService.getTrainingById(id);
        if (training == null) {
            throw new RuntimeException("Тренировка с идентификатором " + id + " не найдена");
        }
        trainingService.delete(id);
        return true;
    }
}
