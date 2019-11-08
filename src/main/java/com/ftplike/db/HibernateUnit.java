package com.ftplike.db;

import com.ftplike.model.User;
import com.ftplike.service.LoggerService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateUnit implements DBase {
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public Boolean containsLogin(String login) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from User where login = :login");
            query.setParameter("login", login);
            List res = query.list();

            return res.size() != 0;
        } catch (Exception ex) {
            LoggerService.log(LoggerService.LogLevels.ERROR, ex.getMessage());
        }
        return null;
    }

    @Override
    public Boolean containsMail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from User where email = :email");
            query.setParameter("email", email);
            List res = query.list();

            return res.size() != 0;
        } catch (Exception ex) {
            LoggerService.log(LoggerService.LogLevels.ERROR, ex.getMessage());
        }
        return null;
    }

    @Override
    public void insertUser(String login, String email, String pass) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(login, email, pass));
            transaction.commit();
        } catch (Exception ex) {
            LoggerService.log(LoggerService.LogLevels.ERROR, ex.getMessage());
        }
    }

    @Override
    public User getUser(String login) {
        String hql = "FROM User u WHERE u.login = :login OR u.email = :login";

        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(hql);
            query.setParameter("login", login);
            User user = (User) query.getSingleResult();

            return user;
        } catch (Exception ex) {
            LoggerService.log(LoggerService.LogLevels.ERROR, ex.getMessage());
        }
        return null;
    }
}
