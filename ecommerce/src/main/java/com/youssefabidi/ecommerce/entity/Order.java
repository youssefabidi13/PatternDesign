package com.youssefabidi.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youssefabidi.ecommerce.strategy.TotalAmountOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="total_price")
    private Double totalPrice;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name="quantity")
    private int quantity;
    @Column(name="date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name="last_updated")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @Transient
    @JsonIgnore

    private TotalAmountOrder totalAmountOrder;

    public Order(TotalAmountOrder totalAmountOrder) {
        this.totalAmountOrder = totalAmountOrder;
    }

    public double calculateTotalAmount(double price, int quantity) {
        return totalAmountOrder.calculateTotalAmount(price, quantity);
    }
    public Order() {
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
}
















