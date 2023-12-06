package com.senla.courses.dto;

import com.senla.courses.entity.User;
import lombok.Data;

@Data
public class AddressDto {
    private Long id;
    private User user;
    private String address;
}
