package com.senla.courses.service;

import com.senla.courses.dto.Schedule;
import com.senla.courses.entity.Training;

import java.util.List;

public interface ScheduleService {


    void addTrainingToSchedule(List<Training> trainingsList);

    void removeTrainingFromSchedule(List<Training> trainingsList);

    Schedule getSchedule();

    String registerToTraining(long userId, long trainingId);
}
