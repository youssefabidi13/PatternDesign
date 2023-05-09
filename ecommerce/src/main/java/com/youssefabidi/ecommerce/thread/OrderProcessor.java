package com.youssefabidi.ecommerce.controller;

import com.youssefabidi.ecommerce.dao.OrderRepository;
import com.youssefabidi.ecommerce.dao.ProductRepository;
import com.youssefabidi.ecommerce.entity.Order;
import com.youssefabidi.ecommerce.entity.Product;

import java.time.LocalDateTime;

public class OrderProcessor implements Runnable {
    private final Order order;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderProcessor(Order order, ProductRepository productRepository, OrderRepository orderRepository) {
        this.order = order;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run() {
        Product product = order.getProduct();
        int currentUnitsInStock = product.getUnitsInStock();
        if (currentUnitsInStock > 0) {
            product.setUnitsInStock(currentUnitsInStock - 1);
            product.setLastUpdated(LocalDateTime.now());
            productRepository.save(product);
            orderRepository.save(order);
        }
    }
}
