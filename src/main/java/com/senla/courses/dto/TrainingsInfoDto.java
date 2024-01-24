package com.senla.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingsInfoDto {
    private Long id;
    private String trainingName;
    private LocalDateTime trainingDate;
    private ScheduleTrainer trainer;
    private Integer totalCount;
    private Integer availableCount;
}
