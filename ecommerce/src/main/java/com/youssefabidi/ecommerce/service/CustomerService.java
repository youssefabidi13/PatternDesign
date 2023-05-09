package com.youssefabidi.ecommerce.service;

import com.youssefabidi.ecommerce.entity.Customer;
import jakarta.transaction.Transactional;

public interface CustomerService {
    Customer save(Customer customer);

    Customer findByEmail(String email);

    Customer findById(Long id);

    @Transactional
    Long findIdByUsername(String username);
}
