package com.codex.task.shop.controller;

import com.codex.task.shop.model.dto.ProductCreateDto;
import com.codex.task.shop.model.dto.TagCreateDto;
import com.codex.task.shop.service.ProductService;
import com.codex.task.shop.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private TagService tagService;
    private ProductService productService;

    @PostMapping("/product")
    public void createProduct(@Valid @RequestBody ProductCreateDto productDto) {
        productService.createProduct(productDto);
    }

    @PostMapping("/tag")
    public void createTag(@Valid @RequestBody TagCreateDto tagDto) {
        tagService.createTag(tagDto);
    }

}
