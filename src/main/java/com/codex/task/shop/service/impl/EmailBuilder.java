package com.codex.task.shop.service.impl;

import com.codex.task.shop.model.dto.ProductChangeDto;
import org.springframework.stereotype.Component;

@Component
public class EmailBuilder {

    public String generateEmail(ProductChangeDto productDto) {
        return "<p>there is a product " + "<i>" + productDto.getName() + "</i>" + " in your cart " +
                "that was changed: </p>" + "<p>" +
                "name: " + productDto.getName() + "</p>" +
                "<p>description: " + productDto.getDescription() + "</p>";
    }
}
