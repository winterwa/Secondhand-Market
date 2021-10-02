package com.laioffer.market.controller;

import com.laioffer.market.entity.Post;
import com.laioffer.market.entity.User;
import com.laioffer.market.request.PostAddRequest;
import com.laioffer.market.response.PostAddResponse;
import com.laioffer.market.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/post/add", method = RequestMethod.POST)
    @ResponseBody
    public PostAddResponse addPost(@RequestBody PostAddRequest addRequest) {
        System.out.println("checkpoint 1");
        return new PostAddResponse(postService.addPost(addRequest));
    }

    @RequestMapping(value = "/post/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Post> getAllPost(@RequestParam(value = "userEmail", required = false) String userEmail) {
        System.out.println("checkpoint 2");
        if (userEmail == null) {
            return postService.getAllPosts();
        } else {
            return postService.getAllPostsByUserId(userEmail);
        }
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Post getPost(@PathVariable(value = "id") int id) {
        System.out.println("checkpoint 2");
        return postService.getPost(id);
    }


    @RequestMapping(value = "/post/markassold/{id}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void markSold(@PathVariable("id") int id){
        postService.markSold(id);
        return;
    }

    @RequestMapping(value = "/post/delete/{id}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePost(@PathVariable("id") int id){
        // call statusService.deletePost(id) to delete this post in database
        postService.deletePost(id);
        return;
    }

}
