package com.senla.courses.controller.impl;

import com.senla.courses.controller.ScheduleController;
import com.senla.courses.dto.Schedule;
import com.senla.courses.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
