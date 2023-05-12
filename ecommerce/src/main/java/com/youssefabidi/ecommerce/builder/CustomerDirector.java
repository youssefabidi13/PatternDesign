package com.youssefabidi.ecommerce.builder;

import com.youssefabidi.ecommerce.entity.Customer;

public class CustomerDirector {
    private Builder builder;

    public CustomerDirector(Builder builder) {
        this.builder = builder;
    }

    public Customer construct() {
        return builder.build();
    }
}

