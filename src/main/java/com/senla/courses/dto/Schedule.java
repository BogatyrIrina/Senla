package com.senla.courses.dto;

import lombok.Data;

import java.util.List;

@Data
public class Schedule {
    private List<TrainingsInfoDto> trainings;
}
