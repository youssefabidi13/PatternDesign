package com.youssefabidi.ecommerce.dao;

import com.youssefabidi.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByEmail(String theEmail);
}
