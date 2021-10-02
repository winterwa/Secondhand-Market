package com.laioffer.market.controller;

import com.laioffer.market.entity.Post;
import com.laioffer.market.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @RequestMapping(value = "/favorite/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getFavorite( ) {
        return favoriteService.getFavorite();
    }


    @RequestMapping(value = "/favorite/{postId}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void favoritePost(@PathVariable("postId") int postId) {
        favoriteService.favoritePost(postId);
    }

    @RequestMapping(value = "/unfavorite/{postId}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void unfavoritePost(@PathVariable("postId") int postId) {
        favoriteService.unfavoritePost(postId);
    }
}


