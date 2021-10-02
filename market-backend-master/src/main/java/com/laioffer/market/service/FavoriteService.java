package com.laioffer.market.service;

import com.laioffer.market.DAO.FavoriteDAO;
import com.laioffer.market.entity.Favorite;
import com.laioffer.market.entity.FavoriteKey;
import com.laioffer.market.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteDAO favoriteDao;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    public List<Post> getFavorite() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = loggedInUser.getName();
        if (userEmail != null) {
            List<Favorite> favorites = favoriteDao.getFavorite(userEmail);
            List<Post> result = new ArrayList<>();
            for (Favorite favorite : favorites) {
                int postId = favorite.getFavoriteKey().getPostId();
                Post post = postService.getPost(postId);
                result.add(post);
            }
            return result;
        }
        return null;
    }

    public void favoritePost(int postId) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userId = loggedInUser.getName();

        FavoriteKey favoriteKey = new FavoriteKey();
        favoriteKey.setPostId(postId);
        favoriteKey.setUserId(userId);
        Favorite favorite = new Favorite();
        favorite.setFavoriteTime(new Date());
        favorite.setFavoriteKey(favoriteKey);
        favoriteDao.favoritePost(favorite);
    }

    public void unfavoritePost(int postId) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userId = loggedInUser.getName();

        FavoriteKey favoriteKey = new FavoriteKey();
        favoriteKey.setPostId(postId);
        favoriteKey.setUserId(userId);
        Favorite favorite = new Favorite();
        favorite.setFavoriteTime(new Date());
        favorite.setFavoriteKey(favoriteKey);
        favoriteDao.unfavoritePost(favorite);
    }

}
