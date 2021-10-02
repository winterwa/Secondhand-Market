package com.laioffer.market.DAO;

import com.laioffer.market.entity.Favorite;
import com.laioffer.market.entity.User;
import com.laioffer.market.service.UserService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FavoriteDAO {
    @Autowired
    private SessionFactory sessionFactory;


//    public List<Favorite> getFavorite() {
//        try (Session session = sessionFactory.openSession()) {
//            return session.createCriteria(Favorite.class)
//                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY) //Deduplicate becaue of restanrant earg fetch
//                    .list();
////            return session.createCriteria(Restaurant.class).list();//this one have duplicates
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return new ArrayList<>();
//    }
    public List<Favorite> getFavorite(String userId) {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM Favorite where userId = :userId";
            return session.createSQLQuery(sql)
                    .addEntity(Favorite.class)
                    .setParameter("userId", userId)
                    .list();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }



    public void favoritePost(Favorite favorite) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(favorite);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void unfavoritePost(Favorite favorite) {

        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.remove(favorite);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}