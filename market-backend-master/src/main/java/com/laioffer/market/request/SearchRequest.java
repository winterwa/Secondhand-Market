package com.laioffer.market.request;

import com.laioffer.market.entity.PriceRange;

public class SearchRequest {
    public String itemName;
    public String category;
    public PriceRange priceRange;
    public String zipCode;
    public String radius;
}
