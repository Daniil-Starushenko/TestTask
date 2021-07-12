package com.codex.task.shop.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
public class TagDto {
    private Integer id;
    private String value;

    @JsonBackReference
    private Set<ProductDto> products;

}