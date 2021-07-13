package com.codex.task.shop.service.impl;

import com.codex.task.shop.model.dto.ProductChangeDto;
import org.springframework.stereotype.Component;

@Component
public class EmailBuilder {

    public String generateEmail(ProductChangeDto productDto) {
        return "there is a product " + productDto + " in your cart" +
                "that was changed: " + System.lineSeparator() +
                productDto.getName() + System.lineSeparator() +
                productDto.getDescription() + System.lineSeparator();
    }
}
