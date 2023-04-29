package com.youssefabidi.ecommerce.dao;

import com.youssefabidi.ecommerce.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
