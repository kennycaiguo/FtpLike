package com.ftplike.db;

import com.ftplike.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateUnit  implements DBase{
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public Boolean containsLogin(String login) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from User where login = :login");
        query.setParameter("login", login);

        List res = query.list();
        transaction.commit();
        session.close();

        return res.size() != 0;
    }

    @Override
    public Boolean containsMail(String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from User where email = :email");
        query.setParameter("email", email);

        List res = query.list();
        transaction.commit();
        session.close();

        return res.size() != 0;
    }

    @Override
    public void insertUser(String login, String email, String pass){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(new User(login, email, pass));

        transaction.commit();
        session.close();
    }

    @Override
    public User getUser(String login){
        String hql = "FROM User u WHERE u.login = :login OR u.email = :login";

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery(hql);
        query.setParameter("login", login);

        User user = (User)query.getSingleResult();

        transaction.commit();
        session.close();

        return user;
    }
}
