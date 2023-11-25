package com.senla.courses.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {
    private Long id;
    private String name;
    private String surname;
    private String specialization;
    private List<Training> trainings;
}




