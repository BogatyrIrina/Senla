package com.senla.courses.controller.impl;

import com.senla.courses.controller.TrainerController;
import com.senla.courses.dto.TrainerDto;
import com.senla.courses.entity.Training;
import com.senla.courses.entity.User;
import com.senla.courses.service.TrainerService;
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
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/trainers")
public class TrainerControllerImpl implements TrainerController {
    private final TrainerService trainerService;
    @PostMapping
    public TrainerDto create(@RequestBody TrainerDto trainerDto) {
        return trainerService.createTrainer(trainerDto);
    }

    @GetMapping("/{id}")
    public TrainerDto getTrainerById(@PathVariable("id") Long id) {
        return trainerService.getTrainerById(id);
    }

    @GetMapping
    public Collection<TrainerDto> trainers() {
        return trainerService.getAllTrainers();
    }

    @PutMapping
    public TrainerDto update(@RequestBody TrainerDto trainerDto) {
        return trainerService.modifyTrainer(trainerDto);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return trainerService.delete(id);
    }

    @GetMapping("/{trainerId}/users")
    public List<User> getUsersByTrainer(@PathVariable("trainerId") Long trainerId) {
        return trainerService.getUsersByTrainer(trainerId);
    }

    @GetMapping("/{trainerId}/trainings")
    public List<Training> getTrainingsByTrainer(@PathVariable("trainerId") Long trainerId) {
        return trainerService.getTrainingsByTrainer(trainerId);
    }
}
