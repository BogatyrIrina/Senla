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
public interface TrainerMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "trainerName", source = "name")
    @Mapping(target = "trainerSurname", source = "surname")
    @Mapping(target = "specialization", source = "specialization")
    TrainerDto toDto(Trainer trainer);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "trainerName")
    @Mapping(target = "surname", source = "trainerSurname")
    @Mapping(target = "specialization", source = "specialization")
    Trainer toEntity(TrainerDto trainerDto);

    default Collection<TrainerDto> toDtoList(Collection<Trainer> trainers) {
        List<TrainerDto> trainerDtoList = new ArrayList<>();
        for (Trainer trainer : trainers) {
            if (trainer.getId() != null) {
                trainerDtoList.add(toDto(trainer));
            }
        }
        return trainerDtoList;
    }

    default UserDto userToUserDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUserName(user.getName());
        userDto.setUserEmail(user.getEmail());

        return userDto;
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
}
