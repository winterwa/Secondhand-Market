package com.laioffer.market.service;

import com.laioffer.market.DAO.ZipCodeDAO;
import com.laioffer.market.entity.ZipCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZipCodeService {

    @Autowired
    private ZipCodeDAO zipCodeDAO;

    public ZipCode getZipCode(String city, String state) {
        return zipCodeDAO.getZipCode(city, state);
    }
}
