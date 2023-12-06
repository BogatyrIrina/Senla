package com.senla.courses.repository.impl;

import com.senla.courses.entity.Trainer;
import com.senla.courses.repository.TrainerRepository;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Subgraph;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class TrainerDao extends AbstractCrudDao<Trainer> implements TrainerRepository {
    public TrainerDao(EntityManager entityManager) {
        super(entityManager, Trainer.class);
    }

    @Override
    public Trainer getById(Long id) {
        EntityGraph graph = this.entityManager.createEntityGraph(Trainer.class);
        Subgraph itemGraph2 = graph.addSubgraph("training");

        Map hints = new HashMap();
        hints.put("javax.persistence.loadgraph", graph);

        return this.entityManager.find(Trainer.class, id, hints);
    }

    @Override
    public Collection<Trainer> getAll() {
        return super.getAll();
    }

    @Override
    public Trainer save(Trainer trainer) {
        return super.save(trainer);
    }

    @Override
    public Trainer update(Trainer trainer) {
        return super.update(trainer);
    }

    @Override
    public boolean delete(Long id) {
        return super.delete(id);
    }
}


