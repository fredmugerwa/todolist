package com.company.dao;

import com.company.config.SessionConfiguration;
import com.company.models.User;
import org.hibernate.Query;
import org.hibernate.Session;

import static com.company.dao.DaoUtils.executeInSession;
import static com.company.dao.DaoUtils.executeInTransaction;

public class UserDAO {

    public void registerUser(User user){
        executeInTransaction(session -> session.save(user));
    }

    public User getUserWithUsernameAndPassword(String username, String password) {
        return (User) executeInSession(session -> {
            String queryString = "FROM User WHERE username = :username AND password = :password";
            Query query = session.createQuery(queryString);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.uniqueResult();
        });
    }

    public User getUserWithUsername(String username) {
        return (User) executeInSession(session -> {
            String queryString = "FROM User WHERE username = :username";
            Query query = session.createQuery(queryString);
            query.setParameter("username", username);
            return query.uniqueResult();
        });
    }
}
