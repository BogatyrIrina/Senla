package com.senla.courses.service.impl;

import com.senla.courses.dto.Schedule;
import com.senla.courses.dto.ScheduleTrainer;
import com.senla.courses.dto.TrainingsInfoDto;
import com.senla.courses.entity.Training;
import com.senla.courses.entity.User;
import com.senla.courses.exeption.RegisterToTrainException;
import com.senla.courses.repository.TrainingRepository;
import com.senla.courses.repository.UserRepository;
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
    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;

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

    @Override
    @Transactional
    public String registerToTraining(long userId, long trainingId) {

        Training training = trainingRepository.getById(trainingId);

        if (training == null) {
            throw new RegisterToTrainException("Training not found");
        }

        User user = userRepository.getById(userId);

        if (training.getDate().compareTo(LocalDateTime.now()) <= 0) {
            throw new RegisterToTrainException("Training ended, you can't register");
        }

        if (userAlreadyRegister(userId, training)) {
            throw new RegisterToTrainException("You have been already registered");
        }

        if (checkFreePlace(training)) {
            throw new RegisterToTrainException("No seats available");
        }

        List<User> users = new ArrayList<>(training.getUsers());
        users.add(user);
        training.setUsers(users);
        trainingRepository.save(training);

        List<Training> trainings = new ArrayList<>(user.getTrainings());
        trainings.add(training);
        user.setTrainings(trainings);
        userRepository.save(user);

        return "You have successfully register for training c id=%s".formatted(trainingId);
    }

    @Transactional
    @Override
    public Schedule getPersonalSchedule(long userId) {

        User user = userRepository.getById(userId);

        List<TrainingsInfoDto> businessTrainings = new ArrayList<>();

        for (Training training : user.getTrainings()) {
            //если дата тренировки меньше текущей то такую тренировку не возвращаем
            if (training.getDate().compareTo(LocalDateTime.now()) <= 0) {
                continue;
            }

            ScheduleTrainer scheduleTrainer = new ScheduleTrainer();
            scheduleTrainer.setName(training.getTrainer().getName());

            TrainingsInfoDto businessTraining = new TrainingsInfoDto();
            businessTraining.setId(training.getId());
            businessTraining.setTrainingName(training.getName());
            businessTraining.setTrainingDate(training.getDate());
            businessTraining.setTrainer(scheduleTrainer);
            businessTraining.setTotalCount(training.getTotalCount());

            Integer availableCount = training.getTotalCount() - training.getUsers().size();
            businessTraining.setAvailableCount(availableCount);

            businessTrainings.add(businessTraining);
        }

        Schedule schedule = new Schedule();
        schedule.setTrainings(businessTrainings);

        return schedule;
    }

    private boolean checkFreePlace(Training training) {
        Integer availableCount = training.getTotalCount() - training.getUsers().size();
        return availableCount == 0;
    }

    private boolean userAlreadyRegister(long userId, Training training) {
        for (User user : training.getUsers()) {
            if (user.getId().equals(userId)) {
                return true;
            }
        }

        return false;
    }
}
