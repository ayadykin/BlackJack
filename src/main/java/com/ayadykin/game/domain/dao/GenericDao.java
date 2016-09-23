package com.ayadykin.game.domain.dao;

import java.io.Serializable;

public interface GenericDao<T, PK extends Serializable> {
   
    T find(long primaryKey);
    
    public void persist(T entity);

    public T merge(T entity);

    public void remove(T entity);

    public void refresh(T entity);

    public void detach(T entity);
}
