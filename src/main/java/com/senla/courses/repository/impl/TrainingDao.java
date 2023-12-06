package com.senla.courses.repository.impl;

import com.senla.courses.entity.Training;
import com.senla.courses.repository.TrainingRepository;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class TrainingDao extends AbstractCrudDao<Training> implements TrainingRepository {
    public TrainingDao(EntityManager entityManager) {
        super(entityManager, Training.class);
    }

    @Override
    public Training getById(Long id) {
        EntityGraph graph1 = this.entityManager.getEntityGraph("graph.Training.trainer");
        EntityGraph graph2 = this.entityManager.getEntityGraph("graph.Training.users");

        Map hints = new HashMap();
        hints.put("javax.persistence.fetchgraph", graph1);
        hints.put("javax.persistence.fetchgraph", graph2);

        return this.entityManager.find(Training.class, id, hints);
    }

    @Override
    public Collection<Training> getAll() {
        return super.getAll();
    }

    @Override
    public Training save(Training training) {
        return super.save(training);
    }

    @Override
    public Training update(Training training) {
        return super.update(training);
    }

    @Override
    public boolean delete(Long id) {
        return super.delete(id);
    }
}
