package com.laioffer.market.DAO;

import com.laioffer.market.entity.Post;
import com.laioffer.market.request.SearchRequest;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.loader.custom.sql.SQLQueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SearchDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Post> search(SearchRequest request) {
        try (Session session = sessionFactory.openSession()) {
            String sql;
            if (request.category.equals("ALL")) {
                sql = "SELECT * FROM Post WHERE item_name = :itemName AND price >= :min AND price <= :max";
                SQLQuery query = session.createSQLQuery(sql);
                query.addEntity(Post.class)
                        .setParameter("itemName", request.itemName)
                        .setParameter("min", request.priceRange.min)
                        .setParameter("max", request.priceRange.max);
                return query.list();
            } else {
                sql = "SELECT * FROM Post WHERE item_name = :itemName AND category = :category AND price >= :min AND price <= :max";
                SQLQuery query = session.createSQLQuery(sql);
                query.addEntity(Post.class)
                        .setParameter("itemName", request.itemName)
                        .setParameter("category", request.category)
                        .setParameter("min", request.priceRange.min)
                        .setParameter("max", request.priceRange.max);
                return query.list();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}