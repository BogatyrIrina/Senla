package com.senla.courses.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private Long id;
    private String userName;
    private String userEmail;
    @ToString.Exclude
    private String password;
    private List<TrainerDto> trainers;

}
