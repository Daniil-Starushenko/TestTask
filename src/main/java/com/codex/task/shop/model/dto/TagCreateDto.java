package com.codex.task.shop.model.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class TagCreateDto {

    @NotEmpty
    private  String value;

}
