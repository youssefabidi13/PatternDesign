package com.youssefabidi.ecommerce.strategy;

public class QuantitySupTen implements TotalAmountOrder{
    @Override
    public double calculateTotalAmount(double price, int quantity) {
        return price * quantity * 0.85;
    }
}
