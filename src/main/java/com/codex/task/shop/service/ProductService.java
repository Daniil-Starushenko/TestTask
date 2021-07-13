package com.codex.task.shop.service;

import com.codex.task.shop.model.dto.ProductChangeDto;
import com.codex.task.shop.model.dto.ProductCreateDto;
import com.codex.task.shop.model.dto.ProductDto;
import com.codex.task.shop.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    void createProduct(ProductCreateDto productDto);

    ProductDto findById(Integer id);

    void updateProduct(Integer id, ProductChangeDto productDto);

    void deleteProductById(Integer id);

    void forceProductUpdate(Integer id, ProductChangeDto productDto);

    Page<Product> filterProducts(Pageable pageable, String tagValue, String description);

}
