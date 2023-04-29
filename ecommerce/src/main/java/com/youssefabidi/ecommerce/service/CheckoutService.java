package com.youssefabidi.ecommerce.service;

import com.youssefabidi.ecommerce.dto.Purchase;
import com.youssefabidi.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
