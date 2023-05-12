package com.youssefabidi.ecommerce.controller;

import com.youssefabidi.ecommerce.dao.ShopRepository;
import com.youssefabidi.ecommerce.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        List<Product> products = shopRepository.findByUnitsInStockNot(0);
        return products;
    }
    @GetMapping("/popular")
    public List<Product> getPopularProducts() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("nbAchat").descending());
        return shopRepository.findAll(pageable).getContent();
    }
    @GetMapping("/latest")
    public List<Product> getNewestProducts() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("lastUpdated").descending());
        return shopRepository.findAll(pageable).getContent();
    }
    @GetMapping("/categories")
    public List<String> getAllCategories() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("lastUpdated").descending());
        return shopRepository.findAllDistinctCategories();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return shopRepository.findById(id).get();
    }

    @GetMapping("/categories/{category}")
    public List<Product> getProductCategory(@PathVariable String category) {
        return shopRepository.findByCategoryAndUnitsInStockNot(category,0);
    }

}
