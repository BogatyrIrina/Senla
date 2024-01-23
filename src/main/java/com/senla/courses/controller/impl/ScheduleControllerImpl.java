package com.senla.courses.controller.impl;

import com.senla.courses.controller.ScheduleController;
import com.senla.courses.dto.Schedule;
import com.senla.courses.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleControllerImpl implements ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/get-schedule")
    public Schedule getSchedule() {
        return scheduleService.getSchedule();
    }

    @PostMapping("/register-to-training")
    public String registerToTraining(Authentication authentication,
                                     @RequestParam(name = "training_id") Long trainingId) {
        return scheduleService.registerToTraining(Long.parseLong(authentication.getName()), trainingId);
    }

    @GetMapping("/get-personal-schedule")
    public Schedule schedule(Authentication authentication) {
        return scheduleService.getPersonalSchedule(Long.parseLong(authentication.getName()));
    }


}
