package com.senla.courses.repository;

import java.util.Collection;

public interface GenericRepository<T, ID> {
    T getById(ID id);
    Collection<T> getAll();
    T save(T entity);
    T update(T entity);
    boolean delete(ID id);
}
