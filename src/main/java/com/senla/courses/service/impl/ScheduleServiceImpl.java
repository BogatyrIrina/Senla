package com.senla.courses.service.impl;

import com.senla.courses.dto.Schedule;
import com.senla.courses.dto.ScheduleTrainer;
import com.senla.courses.dto.TrainingsInfoDto;
import com.senla.courses.entity.Training;
import com.senla.courses.repository.TrainingRepository;
import com.senla.courses.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private ScheduleService scheduleService;
    private final TrainingRepository trainingRepository;

    @Override
    public void addTrainingToSchedule(List<Training> trainingsList) {
        scheduleService.addTrainingToSchedule(trainingsList);
    }

    @Override
    public void removeTrainingFromSchedule(List<Training> trainingsList) {
        scheduleService.removeTrainingFromSchedule(trainingsList);
    }

    @Override
    @Transactional
    public Schedule getSchedule() {

        List<TrainingsInfoDto> trainingsInfo = new ArrayList<>();

        Collection<Training> trainings = trainingRepository.getAll();
        for (Training training : trainings) {
            //если дата тренировки меньше текущей то такую тренировку не возвращаем
            if (training.getDate().compareTo(LocalDateTime.now()) <= 0) {
                continue;
            }

            ScheduleTrainer scheduleTrainer = new ScheduleTrainer();
            scheduleTrainer.setName(training.getTrainer().getName());

            TrainingsInfoDto trainingInfo = new TrainingsInfoDto();
            trainingInfo.setId(training.getId());
            trainingInfo.setTrainingName(training.getName());
            trainingInfo.setTrainingDate(training.getDate());
            trainingInfo.setTrainer(scheduleTrainer);
            trainingInfo.setTotalCount(training.getTotalCount());

            Integer availableCount = training.getTotalCount() - training.getUsers().size();
            trainingInfo.setAvailableCount(availableCount);

            trainingsInfo.add(trainingInfo);
        }

        Schedule schedule = new Schedule();
        schedule.setTrainings(trainingsInfo);

        return schedule;
    }


//    public void addTrainingFromSchedule(List<Training> trainingsList) {
//        trainings.add(trainingsList);
//    }
//
//    public void removeTrainingFromSchedule(Training training) {
//        trainings.remove(training);
//    }
//
//    public void addTrainerFromSchedule(Trainer trainer) {
//        trainers.add(trainer);
//    }
//
//    public void removeTrainerFromSchedule(Trainer trainer) {
//        trainers.remove(trainer);
//    }
//
//
//
//    @Override
//    public void addUserToTraining(User user, Trainings training) {
//        // Логика добавления пользователя на тренировку
//        user.getTrainings().add(training);
//    }
//
//    @Override
//    public void removeUserFromTraining(User user, Trainings training) {
//        // Логика удаления пользователя с тренировки
//        user.getTrainings().remove(training);
//    }
}
