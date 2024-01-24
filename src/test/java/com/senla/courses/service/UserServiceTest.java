package com.senla.courses.service;

import com.senla.courses.dto.UserDto;
import com.senla.courses.entity.User;
import com.senla.courses.mapper.UserMapper;
import com.senla.courses.mapper.UserMapperImpl;
import com.senla.courses.repository.TrainingRepository;
import com.senla.courses.repository.UserRepository;
import com.senla.courses.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private final UserRepository userRepository = mock(UserRepository.class);
    private final TrainingRepository trainingRepository = mock(TrainingRepository.class);
    private final UserMapper userMapper = new UserMapperImpl();

    private final UserService userService = new UserServiceImpl(userRepository, trainingRepository,userMapper);

    @BeforeEach
    public void resetMocks() {
        reset(userRepository);
    }

    @Test
    public void test_should_use_save() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);

        userService.createUser(userDto);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void test_should_use_get_by_id() {
        long id = 1L;
        String name = "John Doe";

        User user = new User();
        user.setName(name);

        when(userRepository.getById(id)).thenReturn(user);

        UserDto userById = userService.getUserById(id);

        verify(userRepository, times(1)).getById(id);

        assertEquals(name, userById.getUserName());
    }

    @Test
    public void test_should_throw_exception() {
        long id = 1L;

        when(userRepository.getById(id)).thenReturn(null);

        assertThrows(Exception.class, () -> userService.getUserById(id));

        verify(userRepository, times(1)).getById(id);

    }
}
