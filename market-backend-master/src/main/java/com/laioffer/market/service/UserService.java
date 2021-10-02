package com.laioffer.market.service;

import com.laioffer.market.DAO.UserDAO;
import com.laioffer.market.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User getUser(String email) {
        return userDAO.getUser(email);
    }

    public void signUp(User user) {
        userDAO.signUp(user);
    }

}
