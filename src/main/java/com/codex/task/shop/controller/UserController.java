package com.codex.task.shop.controller;

import com.codex.task.shop.model.dto.ProductDto;
import com.codex.task.shop.service.CartService;
import com.codex.task.shop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private ProductService productService;

    private CartService cartService;

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

}
