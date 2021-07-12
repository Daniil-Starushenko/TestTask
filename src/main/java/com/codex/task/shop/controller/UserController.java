package com.codex.task.shop.controller;

import com.codex.task.shop.model.dto.ProductDto;
import com.codex.task.shop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private ProductService productService;

    @GetMapping("/product/{id}")
    public ProductDto showProduct(@RequestParam("id") Integer id) {
       return productService.findById(id);
    }

}
