package com.laioffer.market.service;

import com.laioffer.market.DAO.ImageDAO;
import com.laioffer.market.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {

    @Autowired
    private PostService postService;

    @Autowired
    private ImageDAO imageDAO;

    public void addImages(List<String> imageUrls, int postId) {
        List<Image> images = imageUrls.stream()
                .map(url -> {
                    Image image = new Image(url, postId);
                    image.setPost(postService.getPost(postId));
                    return image;
                })
                .collect(Collectors.toList());
        imageDAO.saveImages(images);
    }
}
