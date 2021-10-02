package com.laioffer.market.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceRange {
    public double min;
    public double max;

    public PriceRange() {

    }

    public PriceRange(double min, double max) {
        this.min = min;
        this.max = max;
    }
}
