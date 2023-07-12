package com.company.dao;

import java.util.List;

import static com.company.dao.DaoUtils.executeInSession;
import static com.company.dao.DaoUtils.executeInTransaction;

public class BaseDao<T> {
    private final Class<T> entityType;

    public BaseDao(Class<T> entityType) {
        this.entityType = entityType;
    }

    public void save(T t){
        executeInTransaction(session -> session.save(t));
    }

    public void update(T t){
        executeInTransaction(session -> session.update(t));
    }

    public void delete(T t){
        executeInTransaction(session -> session.delete(t));
    }

    public T getTodo(long id){
        return executeInSession(session -> (T) session.get(entityType, id));
    }

    public List<T> getAllTodos() {
        return executeInSession(session -> session.createCriteria(entityType)
                .list());
    }
}
