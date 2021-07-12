package com.codex.task.shop.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode
public class ProductDto {
    private String name;
    private String description;

    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private Set<TagDto> tags;

}
