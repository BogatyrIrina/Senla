package com.senla.courses.repository.impl;

import com.senla.courses.entity.Trainer;
import com.senla.courses.repository.TrainerRepository;

import java.util.ArrayList;
import java.util.List;

public class TrainerRepositoryImpl implements TrainerRepository {
    private static final Trainer trainer1 = new Trainer();
    private static final Trainer trainer2 = new Trainer();
    private static final Trainer trainer3 = new Trainer();
    private static final Trainer trainer4 = new Trainer();


    private static final List<Trainer> trainerList = new ArrayList<>();
    private Long nextId = 1L;
    public TrainerRepositoryImpl(){
        trainerList.add(trainer1);
        trainerList.add(trainer2);
        trainerList.add(trainer3);
        trainerList.add(trainer4);
    }

    @Override
    public Trainer getTrainer(Long id) {
        return trainerList.stream().filter(trainer -> trainer.getId()
                        .equals(id))
                .toList().get(0);
    }

    @Override
    public List<Trainer> getAllTrainers() {
        return trainerList;
    }

    @Override
    public Long saveTrainer(Trainer trainer) {
        trainer.setId(nextId);
        trainerList.add(trainer);
        nextId++;
        return trainer.getId();
    }

    @Override
    public Trainer modifyTrainer(Trainer trainer, Long id) {
        Trainer existingTrainer = trainerList.stream().
                filter(user5 -> user5.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (existingTrainer != null){
            existingTrainer.setName(trainer.getName());
            existingTrainer.setSpecialization(trainer.getSpecialization());
        }
        return existingTrainer;
    }

    @Override
    public void deleteTrainer(Long id) {
        trainerList.removeIf(trainer -> trainer.getId().equals(id));
    }
}
