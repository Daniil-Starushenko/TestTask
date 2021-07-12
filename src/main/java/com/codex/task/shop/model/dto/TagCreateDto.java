package com.codex.task.shop.model.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@ToString
public class TagCreateDto {

    @NotEmpty
    private  String value;

}
