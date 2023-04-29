package com.youssefabidi.ecommerce.controller;

import com.youssefabidi.ecommerce.dao.AuthorityRepository;
import com.youssefabidi.ecommerce.entity.Authority;
import com.youssefabidi.ecommerce.entity.Customer;
import com.youssefabidi.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    private CustomerService customerService;
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    private AuthorityRepository authorityRepository;

    @PostMapping("/register")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer.Builder customerBuilder) {
        String email = customerBuilder.getEmail();
        String firstName = customerBuilder.getFirstName();
        String lastName = customerBuilder.getLastName();
        String password = customerBuilder.getPassword();
        Set<Authority>  authorities = new HashSet<>();
        System.out.println( customerBuilder.getAuthorities());
        Customer existingCustomer = customerService.findByEmail(email);
        if (existingCustomer != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer customer = customerBuilder.build();
        authorities.add(new Authority("ROLE_USER",customer));
        customerService.save(customer);
        authorityRepository.saveAll(authorities);


        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
}