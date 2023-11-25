package com.senla.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDto {
    private Long id;
    public String trainerName;
    public String trainerSurname;
    public String specialization;
    public List<UserDto> users;
    public List<TrainingDto> trainings;
}
