package com.laioffer.market.DAO;

import com.laioffer.market.entity.Image;
import com.laioffer.market.entity.Post;
import com.laioffer.market.entity.PostStatus;
import com.laioffer.market.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public int savePost(Post post) {
        Session session = null;
        Integer postId = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            postId = (Integer) session.save(post);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return postId;
    }

    public List<Post> getAllPostsByUserId(String userEmail) {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM Post WHERE user_email = :userEmail";
            return (List<Post>) session.createSQLQuery(sql)
                    .addEntity(Post.class)
                    .setParameter("userEmail", userEmail).list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Post> getAllPosts() {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM Post";
            return (List<Post>) session.createSQLQuery(sql)
                    .addEntity(Post.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Post getPost(int id) {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM Post WHERE id = :id";
            return (Post) session.createSQLQuery(sql)
                    .addEntity(Post.class)
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void markAsSold(int postId){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            // get the post using postId
            Post post = session.get(Post.class, postId);
            transaction.commit();
            // update status in this post
            post.setStatus(PostStatus.SOLD);
            Transaction transaction1 = session.beginTransaction();
            session.update(post);
            transaction1.commit();
        }catch (Exception exception){
            exception.printStackTrace();
            if (session != null){
                session.getTransaction().rollback();
            }
        }finally {
            if (session != null){
                session.close();
            }
        }
        return;
    }

    public void deletePost(int id){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            Post post = session.get(Post.class, id);
            User user = post.getUser();
            // remove the cartItem from list
            user.getPostList().remove(post);

            session.beginTransaction();
            session.delete(post);
            session.getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
            if (session != null){
                session.getTransaction().rollback();
            }
        }finally {
            if (session != null){
                session.close();
            }
        }
    }


}
