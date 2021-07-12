package com.codex.task.shop.service.impl;

import com.codex.task.shop.exception.entity.EntityIsExistException;
import com.codex.task.shop.model.dto.ProductCreateDto;
import com.codex.task.shop.repository.mysql.ProductRepository;
import com.codex.task.shop.repository.mysql.TagRepository;
import com.codex.task.shop.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    ModelMapper modelMapper;

    ProductRepository productRepository;

    TagRepository tagRepository;

    @Override
    public void createProduct(ProductCreateDto productDto) {
        String productName = productDto.getName();
        if (productRepository.existsByName(productName)) {
            log.info("product with  name {} is exist", productName);
            throw new EntityIsExistException("product with name " + productName + "is exist");
        }


    }

}
