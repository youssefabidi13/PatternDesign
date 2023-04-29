package com.youssefabidi.ecommerce.controller;

import com.youssefabidi.ecommerce.dao.ProductRepository;
import com.youssefabidi.ecommerce.dao.ShopRepository;
import com.youssefabidi.ecommerce.entity.Image;
import com.youssefabidi.ecommerce.entity.Product;
import com.youssefabidi.ecommerce.entity.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")

public class ShopController {
    @Autowired
    private ShopRepository shopRepository;

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        List<Product> products = shopRepository.findAll();
        return products;
    }
}
