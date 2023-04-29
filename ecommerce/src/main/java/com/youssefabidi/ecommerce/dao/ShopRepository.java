package com.youssefabidi.ecommerce.dao;

import com.youssefabidi.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Product, Long> {
    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> findAllDistinctCategories();

    Optional<Product> findById(@RequestParam("id") Long id);
    Optional<Product> findByCategory(@RequestParam("category") String category);

}
