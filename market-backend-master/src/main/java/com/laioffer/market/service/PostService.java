package com.laioffer.market.service;

import com.laioffer.market.DAO.PostDAO;
import com.laioffer.market.entity.Category;
import com.laioffer.market.entity.Post;
import com.laioffer.market.entity.PostStatus;
import com.laioffer.market.request.PostAddRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private ZipCodeService zipCodeService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private PostDAO postDAO;

    public int addPost(PostAddRequest addRequest) {
        Post post = new Post();
        post.setTitle(addRequest.title);
        post.setStatus(PostStatus.ON_SALE);
        post.setItemName(addRequest.itemName);
        post.setCategory(Category.valueOf(addRequest.category));
        post.setDescription(addRequest.description);
        post.setPreviewImageUrl(addRequest.previewImageUrl);
        post.setPrice(addRequest.price);
        post.setZipCode(zipCodeService.getZipCode(addRequest.city, addRequest.state));
        post.setUser(userService.getUser(addRequest.userEmail));
        post.setPostTime(new Date());
        int postId = postDAO.savePost(post);

        imageService.addImages(addRequest.detailedImageUrls, postId);

        return postId;
    }

    public List<Post> getAllPostsByUserId(String userEmail) {
        return postDAO.getAllPostsByUserId(userEmail);
    }

    public List<Post> getAllPosts() {
        return postDAO.getAllPosts();
    }

    public Post getPost(int postId) {
        return postDAO.getPost(postId);
    }

    public void markSold(int postId){
        postDAO.markAsSold(postId);
        return;
    }

    public void deletePost (int id){
        postDAO.deletePost(id);
        return;
    }
}
