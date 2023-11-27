package com.senla.courses.mapper;

import com.senla.courses.dto.TrainerDto;
import com.senla.courses.entity.Trainer;
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
}
