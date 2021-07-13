package com.codex.task.shop.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductPageDto {

    private Integer page;
    private Integer pageLimit;
    private long totalRecords;
    private List<ProductDto> products;

}
