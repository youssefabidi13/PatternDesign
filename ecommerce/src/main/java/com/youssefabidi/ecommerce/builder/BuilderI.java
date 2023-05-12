package com.youssefabidi.ecommerce.builder;

import com.youssefabidi.ecommerce.entity.Authority;
import com.youssefabidi.ecommerce.entity.Customer;
import com.youssefabidi.ecommerce.entity.Order;

import java.util.Set;

public interface BuilderI {
    BuilderI firstName(String firstName);
    BuilderI lastName(String lastName);
    BuilderI email(String email);
    BuilderI bio(String bio);
    BuilderI phoneNumber(String phoneNumber);
    BuilderI profilePhoto(String profilePhoto);
    BuilderI password(String password);
    BuilderI authorities(Set<Authority> authorities);
    BuilderI orders(Set<Order> orders);
    Customer build();
}
