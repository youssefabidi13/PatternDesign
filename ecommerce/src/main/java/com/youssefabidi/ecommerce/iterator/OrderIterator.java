package com.youssefabidi.ecommerce.iterator;

import com.youssefabidi.ecommerce.entity.Order;

import java.util.LinkedList;
import java.util.Set;

public class OrderIterator implements IIterator {
    private final LinkedList<Order> orders;

    public OrderIterator(Set<Order> orders) {
        this.orders = new LinkedList<>(orders);
    }

    @Override
    public boolean hasNext() {
        return orders.size() > 0;
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            Order order = orders.get(0);
            orders.remove(0);
            return order;
        } else {
            return null;
        }
    }
}
