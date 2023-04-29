package com.youssefabidi.ecommerce.service;

import com.youssefabidi.ecommerce.entity.Customer;

public interface CustomerService {
    Customer save(Customer customer);

    Customer findByEmail(String email);
}
