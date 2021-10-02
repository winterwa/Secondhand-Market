package com.laioffer.market.DAO;

import com.laioffer.market.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public User getUser(String email) {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM User WHERE email = :email";
            return (User) session.createSQLQuery(sql)
                    .addEntity(User.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void signUp(User user) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
