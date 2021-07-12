package com.codex.task.shop.service.impl;

import com.codex.task.shop.exception.entity.EntityIsExistException;
import com.codex.task.shop.exception.entity.EntityNotFoundException;
import com.codex.task.shop.model.dto.ProductChangeDto;
import com.codex.task.shop.model.dto.ProductCreateDto;
import com.codex.task.shop.model.dto.ProductDto;
import com.codex.task.shop.model.entity.Product;
import com.codex.task.shop.model.entity.Tag;
import com.codex.task.shop.repository.mysql.ProductRepository;
import com.codex.task.shop.repository.mysql.TagRepository;
import com.codex.task.shop.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@Transactional
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

    @Override
    @Transactional(readOnly = true)
    public ProductDto findById(Integer id) {
        log.info("try to find product with id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("product with id is not  found: " + id));
        return modelMapper.map(product, ProductDto.class);
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

    @Override
    public void updateProduct(Integer id, ProductChangeDto productDto) {
        Product productToChange = productRepository.getById(id);
        log.info("try to change info in product with id {}", id);
        productToChange.setName(productDto.getName());
        productToChange.setDescription(productDto.getDescription());
        productToChange.getTags().clear();
        if (productDto.getTags().length != 0) {
            productToChange.setTags(findTags(productDto.getTags()));
        }
        productRepository.save(productToChange);
    }

    @Override
    public void deleteProductById(Integer id) {
        log.info("try to delete product with id {}", id);
        productRepository.deleteById(id);
    }

}
