package com.youssefabidi.ecommerce.service;

import com.youssefabidi.ecommerce.dao.CustomerRepository;
import com.youssefabidi.ecommerce.dto.Purchase;
import com.youssefabidi.ecommerce.dto.PurchaseResponse;
import com.youssefabidi.ecommerce.entity.Customer;
import com.youssefabidi.ecommerce.entity.Order;
import com.youssefabidi.ecommerce.entity.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        //retrieve the order info from dto
        Order order = purchase.getOrder();

        //generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        //populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        //populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        //populate customer with order
        Customer customer = purchase.getCustomer();

        //check if this is an existing customer
        String theEmail = customer.getEmail();
        Customer customerFromDB = customerRepository.findByEmail(theEmail);
        if (customerFromDB!=null){
            //we found them
            customer = customerFromDB;
        }

        customer.add(order);

        //save to database
        customerRepository.save(customer);

        //return a response

        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        // generate a random UUID number (UUID version -4)
        return UUID.randomUUID().toString();
    }

}















