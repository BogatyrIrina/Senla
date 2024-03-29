package com.senla.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDto {
    private Long id;
    private String trainingName;
    private LocalDateTime trainingDate;
    private TrainerDto trainer;
    private List<UserDto> users;

}
