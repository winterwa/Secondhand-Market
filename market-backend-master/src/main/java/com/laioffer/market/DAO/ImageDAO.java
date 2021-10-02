package com.laioffer.market.DAO;

import com.laioffer.market.entity.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveImages(List<Image> images) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            for(Image image : images) {
                session.save(image);
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
