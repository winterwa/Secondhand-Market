package com.laioffer.market.controller;


import com.laioffer.market.entity.Post;
import com.laioffer.market.request.SearchRequest;
import com.laioffer.market.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/post/search", method = RequestMethod.POST)
    @ResponseBody
    public List<Post> searchPosts(@RequestBody SearchRequest searchRequest) {
        return searchService.searchPosts(searchRequest);
    }
}
