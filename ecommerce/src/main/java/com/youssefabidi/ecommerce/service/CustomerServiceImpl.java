package com.youssefabidi.ecommerce.service;

import com.youssefabidi.ecommerce.dao.CustomerRepository;
import com.youssefabidi.ecommerce.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    @Transactional
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public Customer findById(Long id) {
    return customerRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Long findIdByUsername(String username) {
        return customerRepository.findIdByUsername(username);
    }
}
