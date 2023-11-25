package com.senla.courses.mapper;

import com.senla.courses.dto.TrainingDto;
import com.senla.courses.entity.Training;
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
public interface TrainingMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "trainingName", source = "name")
    @Mapping(target = "trainingTime", source = "time")
    TrainingDto toDto(Training training);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "trainingName")
    @Mapping(target = "time", source = "trainingTime")
    Training toEntity(TrainingDto trainingDto);
    default Collection<TrainingDto> toDtoList(Collection<Training> trainings) {
        List<TrainingDto> trainingDtoList = new ArrayList<>();
        for (Training training : trainings) {
            if (training.getId() != null) {
                trainingDtoList.add(toDto(training));
            }
        }
        return trainingDtoList;
    }
}
