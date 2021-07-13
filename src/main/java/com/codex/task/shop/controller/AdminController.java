package com.codex.task.shop.controller;

import com.codex.task.shop.model.dto.ProductChangeDto;
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

    @PatchMapping("/product/{id}")
    public void changeProduct(@RequestParam("id") Integer id,
                              @Valid @RequestBody ProductChangeDto productDto) {
        productService.updateProduct(id, productDto);
    }

    @PatchMapping("/product/forceUpdate/{id}")
    public void forceChangeProduct(@RequestParam("id") Integer id,
                                   @Valid @RequestBody ProductChangeDto productDto) {
        productService.forceProductUpdate(id, productDto);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@RequestParam("id") Integer id) {
        productService.deleteProductById(id);
    }

}
