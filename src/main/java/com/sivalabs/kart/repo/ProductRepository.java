package com.sivalabs.kart.repo;

import com.sivalabs.kart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
