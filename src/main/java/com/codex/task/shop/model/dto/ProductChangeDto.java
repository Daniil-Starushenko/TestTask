package com.codex.task.shop.model.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class ProductChangeDto {

    @NotNull
    private String name;

    @NotNull
    private String description;

    private String[] tags;

}
