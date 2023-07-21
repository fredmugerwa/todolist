package com.company.dao;

import com.company.config.SessionConfiguration;
import com.company.models.Todo;
import com.company.models.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    public List<Todo> getTodos(User user) {
        return (List<Todo>) executeInSession(session -> {
            String queryString = "FROM Todo WHERE user = :user";
            Query query = session.createQuery(queryString);
            query.setParameter("user", user);
            return query.list();
        });
    }

    public List<Todo> getTodos(String searchTerm) {
        return executeInSession(session -> {
            String queryString = "FROM Todo t WHERE t.title LIKE :searchTerm";
            Query query = session.createQuery(queryString);
            query.setParameter("keyword", "%" + searchTerm + "%");
            return query.list();
        });
    }
}
