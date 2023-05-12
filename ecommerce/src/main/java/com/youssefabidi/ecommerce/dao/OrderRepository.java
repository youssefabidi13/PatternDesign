package com.youssefabidi.ecommerce.dao;

import com.youssefabidi.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{
    @Query("SELECT o FROM Order o WHERE o.customer.id = :id")
    List<Order> findByCustomerId(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Order o SET o.quantity = CASE WHEN o.quantity > 1 THEN o.quantity - 1 ELSE 0 END WHERE o.id = :id")
    void decrementQuantityOrDelete(@Param("id") Long id);

}
