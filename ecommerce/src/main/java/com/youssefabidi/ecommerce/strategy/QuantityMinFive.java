package com.youssefabidi.ecommerce.strategy;

public class QuantityMinFive implements TotalAmountOrder{
    @Override
    public double calculateTotalAmount(double price, int quantity) {
        return  price * quantity * 0.95 ;
    }
}
