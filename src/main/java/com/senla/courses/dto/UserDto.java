package com.senla.courses.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private Long id;
    private String userName;
    @ToString.Exclude
    private String password;
}
