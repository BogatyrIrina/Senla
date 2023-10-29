package com.senla.courses;

import com.senla.courses.dto.UserDto;
import com.senla.courses.entity.User;
import com.senla.courses.mapper.UserMapper;
import com.senla.courses.mapper.impl.UserMapperImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class UserMapperTest {
    private UserMapper userMapper = new UserMapperImpl();

    @Test
    public void testUserMapping() {
        // Создание пользователя
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        // Преобразование пользователя в DTO
        UserDto userDto = userMapper.toDto(user);

        // Проверка соответствия значений
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getName(), userDto.getUserName());
    }
}

