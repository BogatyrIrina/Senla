package com.senla.courses.service.impl;

import com.senla.courses.dto.TrainingDto;
import com.senla.courses.dto.UserDto;
import com.senla.courses.entity.Training;
import com.senla.courses.entity.User;
import com.senla.courses.exeption.TrainerNotFoundException;
import com.senla.courses.exeption.TrainingNotFoundException;
import com.senla.courses.exeption.UserNotFoundException;
import com.senla.courses.mapper.UserMapper;
import com.senla.courses.repository.TrainingRepository;
import com.senla.courses.repository.UserRepository;
import com.senla.courses.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.getById(id);
        if (user == null) {
            throw new UserNotFoundException("Trying to get a non-existing user with id= [" + id + "]");
        }
        return userMapper.toDto(user);
    }

    @Transactional
    @Override
    public Collection<UserDto> getAllUsers() {
        return userMapper.toDtoList(userRepository.getAll());
    }

    @Transactional
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User cseatedUser = userRepository.save(user);
        return userMapper.toDto(cseatedUser);
    }

    @Transactional
    @Override
    public UserDto modifyUser(UserDto userDto) {

        User user = userRepository.getById(userDto.getId());

        if (user == null) {
            throw new RuntimeException("User with id " + userDto.getId() + " not found");
        }
        user.setName(userDto.getUserName());
        user.setEmail(userDto.getUserEmail());

        User updateUser = userRepository.update(user);

        return userMapper.toDto(updateUser);
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User id can not be null");
        }
        User user = userRepository.getById(id);
        if (user == null) {
            throw new RuntimeException("User with id " + id + " not found");
        }
        return userRepository.delete(id);
    }
    @Transactional
    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.getByEmail(email);
        if (user == null) {
            throw new RuntimeException("User with email " + email + " not found");
        }
        return userMapper.toDto(user);
    }

    @Override
    public boolean addUserToTraining(Long userId, Long trainingId) {
        User user = userRepository.getById(userId);
        if (user == null) {
            throw new UserNotFoundException("User with id " + userId + " not found");
        }

        Training training = trainingRepository.getById(trainingId);
        if (training == null) {
            throw new TrainingNotFoundException("Training with id " + trainingId + " not found");
        }

        if (user.getTrainings().contains(training)) {
            return false; // Уже зарегистрирован
        }

        user.getTrainings().add(training);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean removeUserFromTraining(Long userId, Long trainingId) {
        User user = userRepository.getById(userId);
        if (user == null) {
            throw new UserNotFoundException("User with id " + userId + " not found");
        }

        Training training = trainingRepository.getById(trainingId);
        if (training == null) {
            throw new TrainingNotFoundException("Training with id " + trainingId + " not found");
        }

        if (!user.getTrainings().contains(training)) {
            return false; // Не зарегистрирован на тренировке
        }

        user.getTrainings().remove(training);
        userRepository.save(user);
        return true;
    }

    @Override
    public List<TrainingDto> getUserSchedule(Long userId) {
        User user = userRepository.getById(userId);
        if (user == null) {
            throw new UserNotFoundException("User with id " + userId + " not found");
        }

        List<Training> trainings = user.getTrainings();
        List<TrainingDto> schedule = new ArrayList<>();

        for (Training training : trainings) {
            TrainingDto trainingDto = new TrainingDto();
            trainingDto.setId(training.getId());
            trainingDto.setTrainingName(training.getName());
            trainingDto.setTrainingDate(training.getDate());
            schedule.add(trainingDto);
        }

        return schedule;
    }


}
