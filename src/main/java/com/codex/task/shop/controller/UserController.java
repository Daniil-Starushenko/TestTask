package com.codex.task.shop.controller;

import com.codex.task.shop.model.dto.ProductDto;
import com.codex.task.shop.model.dto.ProductPageDto;
import com.codex.task.shop.model.entity.Product;
import com.codex.task.shop.service.CartService;
import com.codex.task.shop.service.ProductService;
import lombok.AllArgsConstructor;
import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private ProductService productService;
    private CartService cartService;
    private ModelMapper modelMapper;

    @GetMapping("/product/{id}")
    public ProductDto showProduct(@RequestParam("id") Integer id) {
        return productService.findById(id);
    }

    @GetMapping("/cart/{productId}")
    public void addProductToCart(@RequestParam("productId") Integer id,
                                 Principal principal) {
        cartService.productToCart(id, principal);
    }

    @DeleteMapping("/cart/{productId}")
    public void deleteProductFromCart(@RequestParam("productId") Integer id,
                                      Principal principal) {
        cartService.deleteFromCart(id, principal);
    }

    @GetMapping("/cart")
    public void buyProductsInCart(Principal principal) {
        cartService.buyProductsInCart(principal);
    }

    @GetMapping("/products")
    public ProductPageDto filterSearch(@RequestParam(required = false) String tag,
                                       @RequestParam(required = false) String description,
                                       Pageable pageable) {
        modelMapper.getConfiguration()
                .setPropertyCondition(context ->
                        !(context.getSource() instanceof PersistentCollection)
                );
        List<ProductDto> products = productService.filterProducts(pageable, tag, description).stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        return ProductPageDto.builder()
                .products(products)
                .build();
    }

}
