package com.senla.courses.repository.impl;

public abstract class AbstractCrudDao<T> {
    @PersistantContext
    EntityManager entityManager;
    public T getById(Long id){
       return entityManager.find(id, long.class);
    }
}
