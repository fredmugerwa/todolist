package com.company.dao;

import com.company.models.Todo;
import org.hibernate.Query;
import org.hibernate.criterion.Order;

import java.util.List;

import static com.company.dao.DaoUtils.executeInSession;
import static com.company.dao.DaoUtils.executeInTransaction;

public class TodoDAO {

    public void save(Todo todo){
        executeInTransaction(session -> session.save(todo));
    }

    public void update(Todo todo){
        executeInTransaction(session -> session.update(todo));
    }

    public void delete(Todo todo){
        executeInTransaction(session -> session.delete(todo));
    }

    public Todo getTodo(long id){
        return executeInSession(session -> (Todo) session.get(Todo.class, id));
    }

    public List<Todo> getAllTodos() {
        return executeInSession(session -> session.createCriteria(Todo.class)
                .list());
    }

    public List<Todo> getTodos(int offset, int limit) {
        return executeInSession(session -> session.createCriteria(Todo.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .addOrder(Order.asc("title"))
                .list());
    }

    public List<Todo> getTodos(String searchTerm) {
        return executeInSession(session -> {
            String queryString = "FROM  Todo t WHERE t.title LIKE :keyword";
            Query query = session.createQuery(queryString);
            query.setParameter("keyword", "%" + searchTerm + "%");
            return query.list();
        });
    }
}
