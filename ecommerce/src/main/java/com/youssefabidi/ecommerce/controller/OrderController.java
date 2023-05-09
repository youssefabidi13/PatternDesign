package com.youssefabidi.ecommerce.controller;

import com.youssefabidi.ecommerce.thread.OrderManyProcessor;
import com.youssefabidi.ecommerce.thread.OrderProcessor;
import com.youssefabidi.ecommerce.dao.OrderRepository;
import com.youssefabidi.ecommerce.dao.ProductRepository;
import com.youssefabidi.ecommerce.entity.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderController(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/add")
    public synchronized ResponseEntity<Order> processOrder(@RequestBody Order order) {
        OrderProcessor orderProcessor = new OrderProcessor(order, productRepository, orderRepository);
        Thread thread = new Thread(orderProcessor);
        thread.start();

        return ResponseEntity.accepted().body(order);
    }
    @PostMapping("/addMany")
    public synchronized ResponseEntity<Order> processOrderMany(@RequestBody Order order) {
        OrderManyProcessor orderManyProcessor = new OrderManyProcessor(order, productRepository, orderRepository);
        Thread thread = new Thread(orderManyProcessor);
        thread.start();

        return ResponseEntity.accepted().body(order);
    }


    @GetMapping("/{id}")
    public List<Order> getOrdersByCustomerId(@PathVariable Long id) {
        return orderRepository.findByCustomerId(id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Order order = orderOptional.get();

            order.getProduct().setUnitsInStock(order.getProduct().getUnitsInStock() + order.getQuantity());
            order.getProduct().setNbAchat(order.getProduct().getNbAchat() - order.getQuantity());
            productRepository.save(order.getProduct());
            orderRepository.delete(order);

        return ResponseEntity.ok().build();
    }

}
