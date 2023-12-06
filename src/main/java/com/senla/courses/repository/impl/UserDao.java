package com.senla.courses.repository.impl;

import com.senla.courses.entity.User;
import com.senla.courses.entity.User_;
import com.senla.courses.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserDao extends AbstractCrudDao<User> implements UserRepository {

    public UserDao(EntityManager entityManager) {
        super(entityManager, User.class);
    }

    /**
     * Запрос Criteria API с использованием сгенерированной модели (hibernate-jpamodelgen)
     * с ленивыми ассоциациями используя fetch
     *
     */
    @Override
    public User getById(Long id) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        root.fetch(User_.trainings, JoinType.INNER);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(User_.id), id));

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    /**
     * Запрос JPQL поиск сущности по email
     * считаем поле email уникальным
     * с ленивыми ассоциациями используя fetch
     */
    @Override
    public User getByEmail(String email) {
        Query q = this.entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.trainers WHERE u.email = :email");
        q.setParameter("email", email);
        return (User) q.getSingleResult();
    }

    @Override
    public Collection<User> getAll() {
        return super.getAll();
    }

    @Override
    public User save(User user) {
        return super.save(user);
    }

    @Override
    public User update(User user) {
        return super.update(user);
    }

    @Override
    public boolean delete(Long id) {
        return super.delete(id);
    }
}
