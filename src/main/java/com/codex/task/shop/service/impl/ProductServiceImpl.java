package com.codex.task.shop.service.impl;

import com.codex.task.shop.exception.entity.EntityIsExistException;
import com.codex.task.shop.exception.entity.EntityNotFoundException;
import com.codex.task.shop.model.dto.ProductCreateDto;
import com.codex.task.shop.model.entity.Product;
import com.codex.task.shop.model.entity.Tag;
import com.codex.task.shop.repository.mysql.ProductRepository;
import com.codex.task.shop.repository.mysql.TagRepository;
import com.codex.task.shop.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ModelMapper modelMapper;
    private ProductRepository productRepository;
    private TagRepository tagRepository;

    @Override
    public void createProduct(ProductCreateDto productDto) {
        String productName = productDto.getName();
        if (productRepository.existsByName(productName)) {
            log.info("product with  name {} is exist", productName);
            throw new EntityIsExistException("product with name " + productName + "is exist");
        }
        Product productToCreate = new Product();
        productToCreate.setName(productName);
        productToCreate.setDescription(productDto.getDescription());
        if (productDto.getTags().length != 0) {
            productToCreate.setTags(findTags(productDto.getTags()));
        }
        productRepository.save(productToCreate);
    }

    private Set<Tag> findTags(String[] tags) {
        Set<Tag> tagSet = new HashSet<>();
        log.info("try to find tags");
        for (String tag : tags) {
            tagSet.add(tagRepository
                    .findByValue(tag.toUpperCase())
                    .orElseThrow(() -> new EntityNotFoundException("there is no such tag"))
            );
        }
        return tagSet;
    }

}
