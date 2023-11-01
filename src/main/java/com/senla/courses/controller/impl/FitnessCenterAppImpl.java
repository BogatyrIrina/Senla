package com.senla.courses.controller.impl;

import com.senla.courses.controller.FitnessCenterApp;
import com.senla.courses.service.ScheduleService;
import com.senla.courses.service.TrainerService;
import com.senla.courses.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class FitnessCenterAppImpl implements FitnessCenterApp {
    private UserService userService;
    private TrainerService trainerService;
    private ScheduleService scheduleService;


    @Override
    public void run() {
    }
}
