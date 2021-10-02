package com.laioffer.market.DAO;

import com.laioffer.market.entity.ZipCode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ZipCodeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public ZipCode getZipCode(String city, String state) {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM ZipCode WHERE city = :city AND state = :state";
            return (ZipCode) session.createSQLQuery(sql)
                    .addEntity(ZipCode.class)
                    .setParameter("city", city)
                    .setParameter("state", state)
                    .uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
