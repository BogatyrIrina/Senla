package com.senla.courses.mapper;

import com.senla.courses.dto.TrainerDto;
import com.senla.courses.dto.TrainingDto;
import com.senla.courses.dto.UserDto;
import com.senla.courses.entity.Trainer;
import com.senla.courses.entity.Training;
import com.senla.courses.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface UserMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "userName", source = "name")
    @Mapping(target = "userEmail", source = "email")
    @Mapping(target = "password", source = "password")
    UserDto toDto(User user);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "userName")
    @Mapping(target = "email", source = "userEmail")
    @Mapping(target = "password", source = "password")
    User toEntity(UserDto userDto);
    default Collection<UserDto> toDtoList(Collection<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users) {
            if (user.getId() != null) {
                userDtoList.add(toDto(user));
            }
        }
        return userDtoList;
    }
    default TrainerDto trainerDtoToTrainer(Trainer trainer) {
        if (trainer == null) {
            return null;
        }

        TrainerDto trainerDto = new TrainerDto();

        trainerDto.setId(trainer.getId());
        trainerDto.setTrainerName(trainer.getName());
        trainerDto.setTrainerSurname(trainer.getSurname());
        trainerDto.setSpecialization(trainer.getSpecialization());

        return trainerDto;
    }

    default TrainingDto trainingToTrainingDto(Training training) {
        if (training == null) {
            return null;
        }

        TrainingDto trainingDto = new TrainingDto();

        trainingDto.setId(training.getId());
        trainingDto.setTrainingName(training.getName());
        trainingDto.setTrainingTime(training.getTime());

        return trainingDto;
    }
    default Training trainingToTrainingDto(TrainingDto trainingDto) {
        if (trainingDto == null) {
            return null;
        }

        Training training = new Training();

        training.setId(trainingDto.getId());
        training.setName(trainingDto.getTrainingName());
        training.setTime(trainingDto.getTrainingTime());

        return training;
    }
}

