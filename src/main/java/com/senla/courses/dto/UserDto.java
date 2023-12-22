package com.senla.courses.dto;


import com.senla.courses.enums.Role;
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
    private List<Role> roles;
    @ToString.Exclude
    private String password;
    private List<TrainerDto> trainers;
    private List<TrainingDto> trainings;
    private List<AddressDto> addresses;

}
