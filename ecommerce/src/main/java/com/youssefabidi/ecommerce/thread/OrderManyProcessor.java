package com.youssefabidi.ecommerce.thread;

import com.youssefabidi.ecommerce.dao.OrderRepository;
import com.youssefabidi.ecommerce.dao.ProductRepository;
import com.youssefabidi.ecommerce.entity.Order;
import com.youssefabidi.ecommerce.entity.Product;
import com.youssefabidi.ecommerce.strategy.QuantityBeetwenFiveAndTen;
import com.youssefabidi.ecommerce.strategy.QuantityMinFive;
import com.youssefabidi.ecommerce.strategy.QuantitySupTen;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;


public class OrderManyProcessor implements Runnable {
    private final Order order;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderManyProcessor(Order order, ProductRepository productRepository, OrderRepository orderRepository) {
        this.order = order;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    private void processOrder() {
        Product product = productRepository.findById(order.getProduct().getId()).orElse(null);
        if (product == null) {
            throw new IllegalArgumentException("Invalid product ID: " + order.getProduct().getId());
        }
        int quantity = order.getQuantity();
        int currentUnitsInStock = product.getUnitsInStock();
        product.setNbAchat(product.getNbAchat() + quantity);
        if (currentUnitsInStock < quantity) {
            throw new IllegalStateException("Not enough units in stock");
        }
        if (quantity < 5) {
            order.setTotalAmountOrder(new QuantityMinFive());
        } else if (quantity < 10) {
            order.setTotalAmountOrder(new QuantityBeetwenFiveAndTen());
        } else {
            order.setTotalAmountOrder(new QuantitySupTen());
        }
        double totalAmount = order.getTotalAmountOrder().calculateTotalAmount(product.getSalesPrice(), quantity);
        order.setTotalPrice(totalAmount);
        product.setUnitsInStock(currentUnitsInStock - quantity);
        product.setLastUpdated(LocalDateTime.now());
        productRepository.save(product);
        orderRepository.save(order);
    }

    public void run() {
        try {
            processOrder();
        } catch (Exception e) {
            System.out.println("Error processing order: " + e.getMessage());
        }
    }
}
