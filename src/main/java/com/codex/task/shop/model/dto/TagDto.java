package com.codex.task.shop.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
public class TagDto {
    private Integer id;
    private String value;

    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Set<ProductDto> products;

}