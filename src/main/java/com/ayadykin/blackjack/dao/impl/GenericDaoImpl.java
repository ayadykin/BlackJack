package com.ayadykin.blackjack.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ayadykin.blackjack.dao.GenericDao;

public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {
    private final Logger logger = LoggerFactory.getLogger(GenericDao.class);

    private final Class<T> persistentClass;

    @PersistenceContext
    private EntityManager em;

    public GenericDaoImpl(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public List<T> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(persistentClass);
        Root<T> rootEntry = cq.from(persistentClass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public T find(long primaryKey) {
        try {
            return em.find(persistentClass, primaryKey);
        } catch (IllegalArgumentException e) {
            logger.error(" error find {} with id : {}", persistentClass, primaryKey);
            return null;
        }
    }

    @Override
    public void persist(T entity) {
        em.persist(entity);
    }

    @Override
    public T merge(T entity) {
        return em.merge(entity);
    }

    @Override
    public void remove(T entity) {
        em.remove(entity);
    }

    @Override
    public void refresh(T entity) {
        em.refresh(entity);
    }

    @Override
    public void detach(T entity) {
        em.detach(entity);
    }

}
