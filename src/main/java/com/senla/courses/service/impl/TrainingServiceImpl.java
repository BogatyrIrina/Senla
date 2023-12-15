package com.senla.courses.service.impl;

import com.senla.courses.dto.TrainingDto;
import com.senla.courses.entity.Training;
import com.senla.courses.exeption.TrainingNotFoundException;
import com.senla.courses.mapper.TrainingMapper;
import com.senla.courses.repository.TrainingRepository;
import com.senla.courses.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;
    @Transactional
    @Override
    public TrainingDto createTraining(TrainingDto trainingDto) {
        Training training = trainingMapper.toEntity(trainingDto);
        Training cseatedTraining = trainingRepository.save(training);
        return trainingMapper.toDto(cseatedTraining);
    }

    @Transactional
    @Override
    public TrainingDto getTrainingById(Long id) {
        Training training = trainingRepository.getById(id);
        if (training == null) {
            throw new TrainingNotFoundException("Попытка получить не существующую тренировку с id= [" + id + "]");
        }
        return trainingMapper.toDto(training);
    }

    @Override
    @Transactional
    public Collection<TrainingDto> getAllTrainings() {
        return trainingMapper.toDtoList(trainingRepository.getAll());
    }

    @Transactional
    @Override
    public TrainingDto modifyTraining(TrainingDto trainingDto) {
        Training training = trainingRepository.getById(trainingDto.getId());

        if (training == null) {
            throw new TrainingNotFoundException("Тренировка с идентификатором " + trainingDto.getId() + " не найдена");
        }
        training.setName(trainingDto.getTrainingName());
        training.setTime(trainingDto.getTrainingTime());

        Training updateTraining = trainingRepository.update(training);

        return trainingMapper.toDto(updateTraining);
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        if (id == null) {
            throw new TrainingNotFoundException("Training id can not be null");
        }
        Training training = trainingRepository.getById(id);
        if (training == null) {
            throw new TrainingNotFoundException("Тренировка с идентификатором " + id + " не найдена");
        }
        return trainingRepository.delete(id);
    }
}
