package com.senla.courses.repository.impl;

import com.senla.courses.entity.Training;
import com.senla.courses.repository.TrainingRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrainingRepositoryImpl implements TrainingRepository {
    private String name;
    private String time;

    @Override
    public Training getTraining(Long id) {
        return null;
    }

    @Override
    public List<Training> getAllTraining() {
        return null;
    }

    @Override
    public Long saveTraining(Training training) {
        return null;
    }

    @Override
    public Training modifyTraining(Training training, Long id) {
        return null;
    }

    @Override
    public void deleteTraining(Long id) {

    }
}
