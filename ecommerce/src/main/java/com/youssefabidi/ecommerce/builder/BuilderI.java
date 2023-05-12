package com.youssefabidi.ecommerce.builder;

import com.youssefabidi.ecommerce.entity.Authority;
import com.youssefabidi.ecommerce.entity.Customer;
import com.youssefabidi.ecommerce.entity.Order;

import java.util.Set;

public interface Builder {
    Builder firstName(String firstName);
    Builder lastName(String lastName);
    Builder email(String email);
    Builder bio(String bio);
    Builder phoneNumber(String phoneNumber);
    Builder profilePhoto(String profilePhoto);
    Builder password(String password);
    Builder authorities(Set<Authority> authorities);
    Builder orders(Set<Order> orders);
    Customer build();
}
