package com.senla.courses.controller.impl;

import com.senla.courses.controller.TrainingController;
import com.senla.courses.dto.TrainingDto;
import com.senla.courses.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/training")
public class TrainingControllerImpl implements TrainingController {
    private final TrainingService trainingService;
    @PostMapping
    public TrainingDto create(@RequestBody TrainingDto trainingDto) {
        return trainingService.createTraining(trainingDto);
    }

    @GetMapping("/{id}")
    public TrainingDto getTrainingById(@PathVariable("id") Long id) {
        return trainingService.getTrainingById(id);
    }

    @GetMapping
    public Collection<TrainingDto> trainings() {
        return trainingService.getAllTrainings();
    }

    @PutMapping
    public TrainingDto update(@RequestBody TrainingDto trainingDto) {
        return trainingService.modifyTraining(trainingDto);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return trainingService.delete(id);
    }
}
