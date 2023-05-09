package com.youssefabidi.ecommerce.thread;

import com.youssefabidi.ecommerce.dao.OrderRepository;
import com.youssefabidi.ecommerce.dao.ProductRepository;
import com.youssefabidi.ecommerce.entity.Order;
import com.youssefabidi.ecommerce.entity.Product;
import com.youssefabidi.ecommerce.strategy.QuantityMinFive;

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
            order.setQuantity(order.getQuantity()+1);
            order.setTotalAmountOrder(new QuantityMinFive());
            double totalAmount = order.getTotalAmountOrder().calculateTotalAmount(product.getSalesPrice(), order.getQuantity());
            order.setTotalPrice(totalAmount);
            productRepository.save(product);
            orderRepository.save(order);
        }
    }
}
