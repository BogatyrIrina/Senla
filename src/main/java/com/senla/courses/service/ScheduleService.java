package com.senla.courses.service;

import com.senla.courses.dto.Schedule;
import com.senla.courses.entity.Training;

import java.util.List;

public interface ScheduleService {

    Schedule getSchedule();

    String registerToTraining(long userId, long trainingId);

    Schedule getPersonalSchedule(long l);
}
