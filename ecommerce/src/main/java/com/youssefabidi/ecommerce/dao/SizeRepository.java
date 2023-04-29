package com.youssefabidi.ecommerce.dao;

import com.youssefabidi.ecommerce.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {
}
