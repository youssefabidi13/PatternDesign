package com.youssefabidi.ecommerce.dto;

import com.youssefabidi.ecommerce.entity.Address;
import com.youssefabidi.ecommerce.entity.Customer;
import com.youssefabidi.ecommerce.entity.Order;
import com.youssefabidi.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
