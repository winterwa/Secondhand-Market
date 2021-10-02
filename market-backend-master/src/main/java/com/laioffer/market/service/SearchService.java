package com.laioffer.market.service;

import com.laioffer.market.DAO.SearchDAO;
import com.laioffer.market.entity.Post;
import com.laioffer.market.request.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    SearchDAO searchDAO;

    public List<Post> searchPosts(SearchRequest searchRequest) {
        List<Post> results = searchDAO.search(searchRequest);
        Collections.sort(results, (Post one, Post two) -> two.getPostTime().compareTo(one.getPostTime()));
        return results;
    }
}