package com.codex.task.shop.repository.mysql;

import com.codex.task.shop.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    boolean existsByName(String name);

}
