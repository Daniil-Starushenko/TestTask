package com.codex.task.shop.repository.mysql;

import com.codex.task.shop.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    boolean existsByName(String name);

}
