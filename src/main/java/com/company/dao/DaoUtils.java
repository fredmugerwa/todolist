package com.company.dao;

import com.company.config.SessionConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DaoUtils {

    public static void executeInTransaction(TransactionAction action) {
        Transaction transaction = null;
        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            action.execute(session);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public static <T> T executeInSession(SessionAction<T> action) {
        try {
            Session session = SessionConfiguration.getSessionFactory().openSession();
            return action.execute(session);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public interface TransactionAction {
        void execute(Session session);
    }

    public interface SessionAction<T> {
        T execute(Session session);
    }
}
