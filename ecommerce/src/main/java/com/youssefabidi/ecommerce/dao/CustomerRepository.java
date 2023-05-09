package com.youssefabidi.ecommerce.dao;

import com.youssefabidi.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByEmail(String theEmail);

    @Query("SELECT u.id FROM Customer u WHERE u.email = :username")
    Long findIdByUsername(@Param("username") String username);

}
