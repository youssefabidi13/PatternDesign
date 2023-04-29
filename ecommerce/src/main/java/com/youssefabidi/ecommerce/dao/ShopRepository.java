package com.youssefabidi.ecommerce.dao;

import com.youssefabidi.ecommerce.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Product, Long> {

}
