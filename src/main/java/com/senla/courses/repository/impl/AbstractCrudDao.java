package com.senla.courses.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.AllArgsConstructor;

import java.util.Collection;

@AllArgsConstructor
public abstract class AbstractCrudDao<T> {
    @PersistenceContext
    EntityManager entityManager;
    private final Class<T> entityClass;

    public T getById(Long id){
       return entityManager.find(entityClass, id);
    }

    public Collection<T> getAll() {
        CriteriaQuery<T> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(entityClass);
        criteriaQuery.from(entityClass);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public boolean delete(Long id) {
        T entity = getById(id);
        if (entity != null) {
            entityManager.remove(entity);
            return true;
        }
        return false;
    }
}