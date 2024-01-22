package com.senla.courses.controller;

import com.senla.courses.dto.Schedule;
import org.springframework.security.core.Authentication;

public interface ScheduleController {
    Schedule getSchedule();
    String registerToTraining(Authentication authentication, Long trainingId);
}
