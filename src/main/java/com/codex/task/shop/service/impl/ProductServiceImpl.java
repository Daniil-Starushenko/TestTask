package com.codex.task.shop.service.impl;

import com.codex.task.shop.exception.entity.DisableChangeEntity;
import com.codex.task.shop.exception.entity.EntityIsExistException;
import com.codex.task.shop.exception.entity.EntityNotFoundException;
import com.codex.task.shop.model.dto.ProductChangeDto;
import com.codex.task.shop.model.dto.ProductCreateDto;
import com.codex.task.shop.model.dto.ProductDto;
import com.codex.task.shop.model.entity.Cart;
import com.codex.task.shop.model.entity.Product;
import com.codex.task.shop.model.entity.Tag;
import com.codex.task.shop.repository.mysql.CartRepository;
import com.codex.task.shop.repository.mysql.ProductRepository;
import com.codex.task.shop.repository.mysql.TagRepository;
import com.codex.task.shop.service.ProductService;
import com.codex.task.shop.service.specification.DescriptionSpecification;
import com.codex.task.shop.service.specification.TagSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private EmailService emailService;
    private EmailBuilder emailBuilder;
    private ModelMapper modelMapper;
    private ProductRepository productRepository;
    private CartRepository cartRepository;
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

    @Override
    public void forceProductUpdate(Integer id, ProductChangeDto productDto) {
        Product productToChange = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("there is no such product to force update"));
        log.info("try to force change info in product with id {}", id);
        List<Cart> carts = cartRepository.findAllByProductsIsContaining(productToChange);
        if (!carts.isEmpty()) {
            carts.forEach(
                    cart -> emailService.send(
                            cart.getUser().getEmail(), emailBuilder.productChangingEmail(productDto))
            );
        }
        productRepository.save(changeInfoFromDto(productToChange, productDto));
    }

    @Override
    public void updateProduct(Integer id, ProductChangeDto productDto) {
        Product productToChange = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("there is no such product to update"));
        log.info("try to change info in product with id {}", id);
        if (!cartRepository.findAllByProductsIsContaining(productToChange).isEmpty()) {
            throw new DisableChangeEntity("product is situated in some cart");
        }
        productRepository.save(changeInfoFromDto(productToChange, productDto));
    }

    private Product changeInfoFromDto(Product product, ProductChangeDto productDto) {
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.getTags().clear();
        if (productDto.getTags().length != 0) {
            product.setTags(findTags(productDto.getTags()));
        }
        return product;
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
    public void deleteProductById(Integer id) {
        log.info("try to delete product with id {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("there is no such products"));

        List<Cart> cartsWithProduct = cartRepository.findAllByProductsIsContaining(product);

        if (!cartsWithProduct.isEmpty()) {
            cartsWithProduct.forEach(
                    cart -> cart.getProducts().remove(product)
            );
            cartsWithProduct.forEach(
                    cart -> cartRepository.save(cart)
            );
        }
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> filterProducts(Pageable pageable, String tagValue, String description) {
        Tag tag = tagRepository.findByValue(tagValue)
                .orElseThrow(() -> new EntityNotFoundException("no such tag"));
        Specification<Product> specification =
                Specification.where(new TagSpecification(tag))
                .and(new DescriptionSpecification(description));
        return productRepository.findAll(specification, pageable);
    }


}
