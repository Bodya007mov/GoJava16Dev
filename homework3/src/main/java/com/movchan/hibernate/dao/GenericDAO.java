package com.movchan.hibernate.dao;

import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

abstract class GenericDAO<T, ID> {

    public abstract Class<T> getEntityClass();

    @Getter
    private final EntityManager entityManager;

    GenericDAO() {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("persistence");

        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        EntityManager entityManager = getEntityManager();
        List<T> entityList = entityManager.createQuery("from " + getEntityClass().getSimpleName()).getResultList();
        entityManager.close();
        return entityList;
    }

    public Optional<T> getById(ID id) {
        EntityManager entityManager = getEntityManager();
        T entityFromDB = entityManager.find(getEntityClass(), id);
        entityManager.close();
        return Optional.ofNullable(entityFromDB);
    }

    public void deleteById(ID id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        T entityFromDB = entityManager.find(getEntityClass(), id);
        entityManager.remove(entityFromDB);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Optional<T> create(T entity) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(entity);
    }

    public void update(T entity, ID id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        T entityFromDB = entityManager.find(getEntityClass(), id);
        entityManager.merge(entity);
        entityManager.persist(entityFromDB);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
