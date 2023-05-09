package com.youssefabidi.ecommerce.strategy;

public interface TotalAmountOrder {
    double calculateTotalAmount(double price, int quantity);
}
