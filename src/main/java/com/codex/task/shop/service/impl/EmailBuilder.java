package com.codex.task.shop.service.impl;

import com.codex.task.shop.model.dto.ProductChangeDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class EmailBuilder {

    public String productChangingEmail(ProductChangeDto productDto) {
        return "<p>there is a product " + "<i>" + productDto.getName() + "</i>" + " in your cart " +
                "that was changed: </p>" + "<p>" +
                "name: " + productDto.getName() + "</p>" +
                "<p>description: " + productDto.getDescription() + "</p>";
    }

    public String productsBuyingEmail(List<String> products) {
        return "<p>You just bought products: </p>" +
                Arrays.toString(products.toArray());
    }
}
