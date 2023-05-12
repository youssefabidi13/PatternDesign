package com.youssefabidi.ecommerce.controller;

import com.youssefabidi.ecommerce.builder.Builder;
import com.youssefabidi.ecommerce.builder.CustomerDirector;
import com.youssefabidi.ecommerce.dao.AuthorityRepository;
import com.youssefabidi.ecommerce.dto.CustomerDTO;
import com.youssefabidi.ecommerce.entity.Authority;
import com.youssefabidi.ecommerce.entity.Customer;
import com.youssefabidi.ecommerce.entity.Order;
import com.youssefabidi.ecommerce.iterator.IContainer;
import com.youssefabidi.ecommerce.iterator.IIterator;
import com.youssefabidi.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<Customer> addCustomer(@RequestBody Builder customerBuilder) {
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
        CustomerDirector director = new CustomerDirector(customerBuilder);

        //Customer customer = customerBuilder.build();
        Customer customer = director.construct();
        authorities.add(new Authority("ROLE_USER",customer));
        customerService.save(customer);
        authorityRepository.saveAll(authorities);


        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        Customer customers = customerService.findByEmail(authentication.getName());
        System.out.println(customers);
        return customers;

    }
    @PutMapping("/user/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDTO customer) {
        Customer existingCustomer = customerService.findById(id);
        if (existingCustomer == null) {
            return ResponseEntity.notFound().build();
        }
        existingCustomer.setBio(customer.getBio());
        existingCustomer.setPhoneNumber(customer.getPhone());
        Customer updatedCustomer = customerService.save(existingCustomer);
        return ResponseEntity.ok(updatedCustomer);
    }
    @RequestMapping("/{username}")
    public Long getUserIdByUsername(@PathVariable String username) {
        return customerService.findIdByUsername(username);
    }

    @GetMapping("/customer/{id}")
   public double getTotalAmountOrders(@PathVariable Long id) {
        IContainer container = customerService.findById(id);
        IIterator iterator = container.createIterator();
        Double totalAmount = 0.0;
        while (iterator.hasNext()){
            Object object = iterator.next();
            totalAmount+= ((Order) object).getTotalPrice();
        }
        return totalAmount;
    }


}