package com.youssefabidi.ecommerce.dto;

import com.youssefabidi.ecommerce.entity.Customer;
import com.youssefabidi.ecommerce.entity.Product;

public class OrderDto {

    private Long customerId;

    private Long productId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
