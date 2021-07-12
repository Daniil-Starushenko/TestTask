package com.codex.task.shop.service;

import com.codex.task.shop.model.dto.ProductChangeDto;
import com.codex.task.shop.model.dto.ProductCreateDto;
import com.codex.task.shop.model.dto.ProductDto;

public interface ProductService {

    void createProduct(ProductCreateDto productDto);

    ProductDto findById(Integer id);

    void updateProduct(Integer id, ProductChangeDto productDto);

    void deleteProductById(Integer id);

}
